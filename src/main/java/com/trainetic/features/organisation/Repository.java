package com.trainetic.features.organisation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface Repository extends JpaRepository<Organisation, UUID> {

    Optional<Organisation> findByOrganisationName(String name);

}
