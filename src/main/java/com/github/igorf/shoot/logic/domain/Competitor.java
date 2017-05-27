package com.github.igorf.shoot.logic.domain;

import lombok.Data;

@Data
public class Competitor {
    private long id;
    private String firstname;
    private String lastname;
    private String login;
    private String password;
}
