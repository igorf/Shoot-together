package com.github.igorf.shoot.logic.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @Transient private String passwordConfirm;
    @ManyToMany
    private Set<Role> roles = new HashSet<>(0);

    @Transient
    public boolean hasRole(long roleID) {
        if (roles != null) {
            for (Role role: roles) {
                if(role.getId() == roleID) {
                    return true;
                }
            }
        }
        return false;
    }
}
