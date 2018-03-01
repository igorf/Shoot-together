package com.github.igorf.shoot.logic.domain;

import com.github.igorf.shoot.misc.CompetitionStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
    private Competitor competitor;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="competition_id")
    private Competition competition;
    private int result;
    private int place;
    private boolean sent = false;
    @OneToMany(cascade=ALL, mappedBy="competitionResult")
    private List<CompetitorTarget> targets;

    @Transient
    public List<Shot> getShots() {
        List<Shot> total = new ArrayList<>();
        if (getTargets() != null) {
            for (CompetitorTarget t : getTargets()) {
                total.addAll(t.getShots());
            }
        }

        return total;
    }

    public boolean needMoreTargets() {
        try {
            int targetsNeeded = getCompetition().getExercise().getShots() / getCompetition().getExercise().getShotsPerTarget();
            return targetsNeeded != 0 && (getTargets() == null || targetsNeeded > getTargets().size());
        } catch (Exception ex) {
            //TODO: log exception
        }
        return false;
    }

    public void addTarget(CompetitorTarget target) {
        if (this.targets == null) {
            this.targets = new ArrayList<>(1);
        }
        this.targets.add(target);
    }

    @Transient
    public int getMinimumVisibleRing() {
        int minimum = (getShots().size() > 0) ? (int) getShots().get(0).getResult() : getCompetition().getExercise().getTarget().minimumDenomination();
        for (Shot s: getShots()) {
            if (s.getResult() < minimum) {
                minimum = (int)s.getResult();
            }
        }
        return minimum - 1;
    }

    @Transient
    public boolean isEditable() {
        return !(isSent() || getCompetition() == null || getCompetition().getStatus() != CompetitionStatus.ACTIVE);
    }

    @Transient
    public boolean isSendable() {
        return isEditable() && !needMoreTargets();
    }
}
