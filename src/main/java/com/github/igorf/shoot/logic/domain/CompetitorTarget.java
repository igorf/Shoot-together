package com.github.igorf.shoot.logic.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
public class CompetitorTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="competitionResult_id")
    private CompetitionResult competitionResult;
    @ManyToOne
    private Profile competitor;
    @NotNull
    private Date dateCreated;
    @OneToMany(cascade=ALL, mappedBy="competitorTarget")
    private List<Shot> shots;
}
