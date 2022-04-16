package com.trainetic.auth.service;

import com.trainetic.entity.User;
import com.trainetic.auth.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
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

    public List<User> findUsersByEmail(String email) {
        return repository.findAllByEmail(email);
    }

    public Optional<User> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }
}
