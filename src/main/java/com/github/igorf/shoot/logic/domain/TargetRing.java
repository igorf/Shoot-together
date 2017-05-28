package com.github.igorf.shoot.logic.domain;

import com.github.igorf.shoot.misc.RingColor;
import lombok.Data;

@Data
public class TargetRing {
    private long id;
    private int value;
    private float diameter;
    private RingColor color;
}
