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
    @JoinColumn(name="competitionTarget_id")
    private CompetitorTarget competitorTarget;
    private float x;
    private float y;
    private float result;
    @NotNull
    private Date dateCreated;
}
