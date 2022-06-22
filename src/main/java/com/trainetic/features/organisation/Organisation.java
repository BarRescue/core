package com.trainetic.features.organisation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trainetic.entity.Auditable;
import com.trainetic.features.auth.RoleType;
import com.trainetic.features.auth.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organisation extends Auditable {

    @NotBlank
    private String organisationName;

    private String organisationAddress;

    private String organisationCity;

    @NotBlank
    private String organisationEmail;

    private String organisationTelephone;

    @OneToOne
    private Permission permission;

    @OneToMany(mappedBy = "organisation")
    @JsonIgnore
    private Set<User> users;
}
