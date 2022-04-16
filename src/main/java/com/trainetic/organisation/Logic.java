package com.trainetic.organisation;

import com.trainetic.entity.Organisation;
import com.trainetic.exception.GeneralException;
import com.trainetic.exception.ResourceNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class Logic {

    private final Service service;

    public Logic(Service service) {
        this.service = service;
    }

    public void organisationExists(String name) {
        Optional<Organisation> organisation = this.service.findOrganisationByName(name);

        if(organisation.isPresent()) {
            throw new GeneralException("Organisation already exists by the given name.");
        }
    }

    public Organisation createOrUpdate(Organisation organisation) {
        return this.service.createOrUpdate(organisation);
    }

    public Organisation getOrganisation(UUID id) {
        return this.service.findOrganisationById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
