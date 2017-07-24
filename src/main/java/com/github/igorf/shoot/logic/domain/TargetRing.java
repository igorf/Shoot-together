package com.github.igorf.shoot.logic.domain;

import com.github.igorf.shoot.misc.RingColor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class TargetRing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private int denomination;
    @NotNull
    private float diameter;
    @NotNull
    private RingColor color;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="target_id")
    private Target target;
}
