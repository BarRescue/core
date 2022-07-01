package com.trainetic.features.organisation;

import com.trainetic.exception.GeneralException;
import com.trainetic.exception.ResourceNotFoundException;
import com.trainetic.features.auth.User;
import com.trainetic.features.auth.logic.UserLogic;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.SecurityContext;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class Logic {

    private final Service service;
    private final ModelMapper modelMapper;
    private final UserLogic userLogic;

    public Logic(Service service, UserLogic userLogic) {
        this.service = service;
        this.userLogic = userLogic;
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

    public OrganisationDTO getOrganisationInfo(SecurityContext ctx) {
        User user = userLogic.getUser(ctx.getUserPrincipal().getName());
        Organisation organisation = user.getOrganisation();

        return OrganisationDTO.builder()
                .organisationName(organisation.getOrganisationName())
                .organisationAddress(organisation.getOrganisationAddress())
                .organisationCity(organisation.getOrganisationCity())
                .organisationEmail(organisation.getOrganisationEmail())
                .organisationTelephone(organisation.getOrganisationTelephone())
                .build();
    }
}
