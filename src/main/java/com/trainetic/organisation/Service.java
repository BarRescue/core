package com.trainetic.organisation;

import com.trainetic.entity.Organisation;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public Organisation createOrUpdate(Organisation organisation) {
        return this.repository.save(organisation);
    }

    public Optional<Organisation> findOrganisationByName(String name) {
        return this.repository.findByOrganisationName(name);
    }

    public Optional<Organisation> findOrganisationById(UUID id) {
        return this.repository.findById(id);
    }
}
