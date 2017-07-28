package com.github.igorf.shoot.logic.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String title;
    @NotNull
    @ManyToOne
    private Exercise exercise;
    @NotNull
    private Date start;
    @NotNull
    private Date end;
    @OneToMany(cascade=ALL, mappedBy="competition")
    private List<CompetitionResult> results;

    @Transient
    public boolean isMutable() {
        return getStart().getTime() > new Date().getTime();
    }
}
