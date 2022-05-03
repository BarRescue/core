package com.trainetic.features.auth.repository;

import com.trainetic.features.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAllByEmail(String email);

    Optional<User> findByUsername(String username);

}
