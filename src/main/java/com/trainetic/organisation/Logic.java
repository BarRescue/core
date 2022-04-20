package com.trainetic.organisation;

import com.trainetic.auth.logic.UserLogic;
import com.trainetic.dto.UserDTO;
import com.trainetic.entity.Organisation;
import com.trainetic.entity.Role;
import com.trainetic.entity.RoleType;
import com.trainetic.entity.User;
import com.trainetic.exception.GeneralException;
import com.trainetic.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class Logic {

    private final Service service;
    private final ModelMapper modelMapper;

    public Logic(Service service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
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
