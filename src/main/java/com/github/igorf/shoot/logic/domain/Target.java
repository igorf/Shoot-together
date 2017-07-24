package com.github.igorf.shoot.logic.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String title;
    @OneToMany(cascade=ALL, mappedBy="target")
    @OrderBy("denomination DESC")
    private List<TargetRing> rings;
}
