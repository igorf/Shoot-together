package com.github.igorf.shoot.logic.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Shot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="competitionResult_id")
    private CompetitionResult competitionResult;
    @ManyToOne
    private Profile competitor;
    private float x;
    private float y;
    private float result;
    @NotNull
    private Date dateCreated;
}
