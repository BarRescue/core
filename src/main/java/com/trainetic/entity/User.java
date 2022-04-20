package com.trainetic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable {
    @NotBlank
    private String email;

    @NotBlank
    @Column(unique = true)
    private String username;

    @JsonIgnore
    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @OneToOne
    private Role role;

    @ManyToOne
    @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    private Organisation organisation;

    @JsonIgnore
    @OneToMany(mappedBy = "coach")
    private Set<User> clients = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    private User coach;

    public User(String email, String username, String password, String firstName, Role role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.role = role;
    }
}
