package com.trainetic.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trainetic.entity.RoleType;
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
