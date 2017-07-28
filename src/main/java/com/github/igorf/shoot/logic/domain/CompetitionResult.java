package com.github.igorf.shoot.logic.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
public class CompetitionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @OneToOne
    private Profile competitor;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="competition_id")
    private Competition competition;
    private int result;
    private int place;
    @OneToMany(cascade=ALL, mappedBy="competitionResult")
    private List<Shot> shots;
}
