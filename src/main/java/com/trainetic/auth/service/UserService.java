package com.trainetic.auth.service;

import com.trainetic.auth.entity.User;
import com.trainetic.auth.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> findUserById(UUID id) {
        return repository.findById(id);
    }

    public User findOrCreate(User user) {
        return repository.save(user);
    }

    public Optional<User> findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

}
