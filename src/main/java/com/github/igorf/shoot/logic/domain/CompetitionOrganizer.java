package com.github.igorf.shoot.logic.domain;

import lombok.Data;

import java.util.List;

@Data
public class CompetitionOrganizer {
    private long id;
    private String title;
    private String description;
    private String url;
    private List<Profile> owners;
}
