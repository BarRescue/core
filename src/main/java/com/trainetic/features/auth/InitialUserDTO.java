package com.trainetic.features.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class InitialUserDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    private String firstName;

    private String organisationName;

    private String organisationAddress;

    private String organisationEmail;

    private String organisationCity;

    private String organisationTelephone;

}
