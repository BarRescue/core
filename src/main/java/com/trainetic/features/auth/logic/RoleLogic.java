package com.trainetic.features.auth.logic;

import com.trainetic.features.auth.service.RoleService;
import com.trainetic.features.auth.Role;
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
            throw new ResourceNotFoundException("Role not found");
        }

        return role.get();
    }
}
