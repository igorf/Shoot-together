package com.github.igorf.shoot.logic.domain;

import lombok.Data;
import java.util.Date;

@Data
public class Shot {
    private long id;
    private Competition competition;
    private Profile competitor;
    private float x;
    private float y;
    private float result;
    private Date dateCreated;
}
