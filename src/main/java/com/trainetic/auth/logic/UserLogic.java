package com.trainetic.auth.logic;

import com.trainetic.auth.service.UserService;
import com.trainetic.dto.InitialUserDTO;
import com.trainetic.dto.UserDTO;
import com.trainetic.entity.Organisation;
import com.trainetic.entity.Role;
import com.trainetic.entity.RoleType;
import com.trainetic.entity.User;
import com.trainetic.exception.GeneralException;
import com.trainetic.exception.ResourceNotFoundException;
import com.trainetic.logic.PermissionRole;
import com.trainetic.logic.TokenLogic;
import com.trainetic.organisation.Logic;
import io.quarkus.elytron.security.common.BcryptUtil;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.SecurityContext;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserLogic {

    private final UserService service;
    private final TokenLogic tokenLogic;
    private final RoleLogic roleLogic;
    private final ModelMapper modelMapper;
    private final Logic organisationLogic;

    public UserLogic(UserService service, TokenLogic tokenLogic, RoleLogic roleLogic, Logic organisationLogic) {
        this.service = service;
        this.tokenLogic = tokenLogic;
        this.roleLogic = roleLogic;
        this.organisationLogic = organisationLogic;
        this.modelMapper = new ModelMapper();
    }

    public User getUser(String id) {
        return service.findUserById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
    }

    public String createInitialUser(InitialUserDTO dto) {
        if(service.findUserByUsername(dto.getUsername()).isPresent()) {
            throw new GeneralException("User already exists.");
        }

        // Get role, if not exists, throw exception.
        Role role = this.roleLogic.findByName(RoleType.ORGANISATION_MANAGER.getValue());

        // If organisation exists, throw exception.
        this.organisationLogic.organisationExists(dto.getOrganisationName());

        Organisation organisation = this.organisationLogic.createOrUpdate(this.modelMapper.map(dto, Organisation.class));

        User user = this.modelMapper.map(dto, User.class);
        user.setOrganisation(organisation);
        user.setPassword(BcryptUtil.bcryptHash(dto.getPassword()));
        user.setRole(role);

        User createdUser = service.findOrCreate(user);

        return tokenLogic.generateToken(createdUser);
    }

    public String loginUser(UserDTO dto) {
        Optional<User> user = service.findUserByUsername(dto.getEmail());

        if(user.isPresent() && BcryptUtil.matches(dto.getPassword(), user.get().getPassword())) {
            return tokenLogic.generateToken(user.get());
        }

        throw new GeneralException("ERROR: Username or password is incorrect.");
    }

    public User createUserForOrganisation(UserDTO dto, String userRequestId) {
        if(service.findUserByUsername(dto.getUsername()).isPresent()) {
            throw new GeneralException("ERROR: User already exists.");
        }

        User requestUser = this.getUser(userRequestId);

        // Get role, if not exists, throw exception.
        Role role = this.roleLogic.findByName(dto.getAccountType().getValue());

        User user = this.modelMapper.map(dto, User.class);
        user.setPassword(BcryptUtil.bcryptHash(dto.getPassword()));
        user.setRole(role);

        if(dto.getAccountType().equals(RoleType.COACH)) {

            if(!requestUser.getRole().getName().equals(PermissionRole.Values.ORGANISATION_MANAGER)) {
                throw new GeneralException("You are not allowed to add another coach to the organisation");
            }

            if(requestUser.getOrganisation().exceedsCoachesLimit()) {
                throw new GeneralException("ERROR: Please increase your subscription to add another coach.");
            }

            user.setOrganisation(requestUser.getOrganisation());
        }

        if(dto.getAccountType().equals(RoleType.NORMAL) && dto.getCoachId() != null) {
            User coach = this.getUser(dto.getCoachId());

            if(!coach.getOrganisation().getOrganisationName().equals(requestUser.getOrganisation().getOrganisationName())) {
                throw new GeneralException("ERROR: Coach organisation doesnt match the requesting user!");
            }

            if(requestUser.getOrganisation().exceedsClientsLimit()) {
                throw new GeneralException("ERROR: Please increase your subscription to add another client.");
            }

            user.setCoach(coach);
        }

        return this.service.findOrCreate(user);
    }
}
