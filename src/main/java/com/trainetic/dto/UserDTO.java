package com.trainetic.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.trainetic.entity.RoleType;
import lombok.Data;

@Data
public class UserDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    private String firstName;

    private RoleType accountType;

    private String organisationId;
}
