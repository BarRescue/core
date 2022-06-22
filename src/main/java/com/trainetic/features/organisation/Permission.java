package com.trainetic.features.organisation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trainetic.entity.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Permission extends Auditable {

    @OneToOne
    private Organisation organisation;

    @JsonIgnore
    private Integer schemeCount = 2;

    @JsonIgnore
    private Integer customerCount = 1;

    @JsonIgnore
    private Integer coachCount = 1;

    public Boolean exceedsCoachesLimit() {
        return this.organisation.getUsers().stream().filter(user -> user.getCoach() == null).count() >= this.coachCount;
    }

    public Boolean exceedsClientsLimit() {
        return this.organisation.getUsers().stream().filter(user -> user.getCoach() != null).count() >= this.customerCount;
    }
}
