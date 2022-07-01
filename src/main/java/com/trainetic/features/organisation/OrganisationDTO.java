package com.trainetic.features.organisation;

import lombok.Builder;

@Builder
public class OrganisationDTO {

    private String organisationName;
    private String organisationAddress;
    private String organisationCity;
    private String organisationEmail;
    private String organisationTelephone;

}
