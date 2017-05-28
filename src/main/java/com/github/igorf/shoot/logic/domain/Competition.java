package com.github.igorf.shoot.logic.domain;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Competition {
    private long id;
    private String title;
    private Exercise exercise;
    private CompetitionOrganizer organizer;
    private Date start;
    private Date end;
    private List<CompetitionResult> results;
}
