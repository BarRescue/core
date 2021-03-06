package com.trainetic.features.auth.service;

import com.trainetic.features.auth.Role;
import com.trainetic.features.auth.repository.RoleRepository;

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
