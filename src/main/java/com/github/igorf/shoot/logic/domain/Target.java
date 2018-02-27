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

    @Transient
    public float getDiameter() {
        if (rings != null) {
            float diameter = rings.get(0).getDiameter();
            for (TargetRing ring: rings) {
                if (ring.getDiameter() > diameter) {
                    diameter = ring.getDiameter();
                }
            }
            return diameter;
        }
        return 0;
    }

    @Transient
    public int minimumDenomination() {
        if (rings != null) {
            int denomination = rings.get(0).getDenomination();
            for (TargetRing ring: rings) {
                if (ring.getDenomination() < denomination) {
                    denomination = ring.getDenomination();
                }
            }
            return denomination;
        }
        return 0;
    }
}
