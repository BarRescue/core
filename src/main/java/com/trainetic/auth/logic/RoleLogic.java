package com.trainetic.auth.logic;

import com.trainetic.auth.service.RoleService;
import com.trainetic.auth.entity.Role;
import com.trainetic.exception.ResourceNotFoundException;

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
