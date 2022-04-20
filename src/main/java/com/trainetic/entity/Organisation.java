package com.trainetic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.List;
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

    @JsonIgnore
    private Integer schemeCount = 2;

    @JsonIgnore
    private Integer customerCount = 1;

    @JsonIgnore
    private Integer coachCount = 1;

    @OneToMany(mappedBy = "organisation")
    @JsonIgnore
    private Set<User> coaches;

    public Boolean exceedsCoachesLimit() {
        return this.coaches.size() >= this.coachCount;
    }

    public Boolean exceedsClientsLimit() {
        int total = this.coaches.stream().map(User::getClients)
                .filter(Objects::nonNull)
                .mapToInt(Set::size)
                .sum();

        return total >= this.customerCount;
    }
}
