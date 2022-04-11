package com.trainetic.service.user;

import com.trainetic.entity.auth.Role;
import com.trainetic.repository.user.RoleRepository;

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
