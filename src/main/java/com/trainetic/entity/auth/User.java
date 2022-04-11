package com.trainetic.entity.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trainetic.entity.Auditable;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

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
}
