package com.trainetic.logic;

import com.trainetic.dto.UserDTO;
import com.trainetic.entity.auth.Role;
import com.trainetic.entity.auth.User;
import com.trainetic.exception.GeneralException;
import com.trainetic.exception.ResourceNotFoundException;
import com.trainetic.service.user.UserService;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.SecurityContext;
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

    public User getUser(SecurityContext ctx) {
        return service.findUserById(UUID.fromString(ctx.getUserPrincipal().getName())).orElseThrow(ResourceNotFoundException::new);
    }

    public String createUser(UserDTO dto) {
        if(service.findUserByEmail(dto.getEmail()).isPresent()) {
            throw new GeneralException("User already exists.");
        }

        System.out.println(dto.getAccountType().getValue());

        Role role = this.roleLogic.findByName(dto.getAccountType().getValue());
        User user = new User(dto.getEmail(), BcryptUtil.bcryptHash(dto.getPassword()), dto.getFirstName(), role, 2, 1);

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

}
