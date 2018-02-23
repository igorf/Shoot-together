package com.github.igorf.shoot.logic.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull private String title;
    private String description;
    @NotNull private int shots;
    @NotNull private int timeLimit; //between first and last shot
    @NotNull private float caliber;
    @NotNull private float distance;
    @NotNull private int shotsPerTarget;
    @ManyToOne
    private Target target;
}
