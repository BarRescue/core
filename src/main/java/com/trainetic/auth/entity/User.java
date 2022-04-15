package com.trainetic.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trainetic.entity.Auditable;
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
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @OneToOne
    private Role role;

    @NotBlank
    private Integer schemeCount;

    @NotBlank
    private Integer customerCount;

    @OneToMany(mappedBy = "coach")
    private Set<User> clients = new HashSet<>();

    public void addClient(User user) {
        this.clients.add(user);
    }
}
