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
    @NotNull
    private Date dateCreated;
    @OneToMany(cascade=ALL, mappedBy="competitorTarget")
    private List<Shot> shots;

    @Transient
    public int getScore() {
        int score = 0;
        if (shots != null) {
            for (Shot s : shots) {
                score += s.getResult();
            }
        }
        return score;
    }

    @Transient
    public int getMinimumVisibleRing() {
        int minimum = (getShots() != null && getShots().size() > 0) ? (int) getShots().get(0).getResult() : getCompetitionResult().getCompetition().getExercise().getTarget().minimumDenomination();
        for (Shot s: getShots()) {
            if (s.getResult() < minimum) {
                minimum = (int)s.getResult();
            }
        }
        return minimum - 1;
    }
}
