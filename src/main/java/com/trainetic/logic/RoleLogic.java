package com.trainetic.logic;

import com.trainetic.entity.auth.Role;
import com.trainetic.exception.ResourceNotFoundException;
import com.trainetic.service.user.RoleService;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class RoleLogic {

    private final RoleService service;

    public RoleLogic(RoleService service) {
        this.service = service;
    }

    public Role findByName(String name) {

        Optional<Role> role = service.findByName(name);

        if(role.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return role.get();
    }
}
