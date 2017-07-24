package com.github.igorf.shoot.logic.domain;

import lombok.Data;

import java.util.List;

@Data
public class CompetitionResult {
    private long id;
    private Profile competitor;
    private Competition competition;
    private int result;
    private int place;
    private List<Shot> shots;
}
