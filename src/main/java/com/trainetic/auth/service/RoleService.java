package com.trainetic.auth.service;

import com.trainetic.auth.entity.Role;
import com.trainetic.auth.repository.RoleRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(String name) {
        return this.roleRepository.findByName(name);
    }
}
