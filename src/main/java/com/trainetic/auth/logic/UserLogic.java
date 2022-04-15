package com.trainetic.auth.logic;

import com.trainetic.auth.service.UserService;
import com.trainetic.auth.dto.UserDTO;
import com.trainetic.auth.entity.Role;
import com.trainetic.auth.entity.User;
import com.trainetic.exception.GeneralException;
import com.trainetic.exception.ResourceNotFoundException;
import com.trainetic.logic.TokenLogic;
import io.quarkus.elytron.security.common.BcryptUtil;

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

    public UserLogic(UserService service, TokenLogic tokenLogic, RoleLogic roleLogic) {
        this.service = service;
        this.tokenLogic = tokenLogic;
        this.roleLogic = roleLogic;
    }

    public User getUser(String id) {
        return service.findUserById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
    }

    public String createUser(UserDTO dto) {
        if(service.findUserByEmail(dto.getEmail()).isPresent()) {
            throw new GeneralException("User already exists.");
        }

        Role role = this.roleLogic.findByName(dto.getAccountType().getValue());
        User user = new User(dto.getEmail(), BcryptUtil.bcryptHash(dto.getPassword()), dto.getFirstName(), role, 2, 1, new HashSet<>());

        User createdUser = service.findOrCreate(user);

        return tokenLogic.generateToken(createdUser);
    }

    public String loginUser(UserDTO dto) {
        Optional<User> user = service.findUserByEmail(dto.getEmail());

        if(user.isPresent() && BcryptUtil.matches(dto.getPassword(), user.get().getPassword())) {
            return tokenLogic.generateToken(user.get());
        }

        throw new GeneralException("Email or password is incorrect.");
    }

    public Boolean isInCoachList(User client, User coach) {
        return coach.getClients().contains(client);
    }
}
