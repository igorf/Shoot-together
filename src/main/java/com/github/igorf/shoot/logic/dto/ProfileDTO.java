package com.github.igorf.shoot.logic.dto;

import com.github.igorf.shoot.logic.domain.Profile;
import lombok.Data;

@Data
public class ProfileDTO {
    private String login;
    private String password;
    private String passwordConfirm;
    private String captcha;

    public Profile createProfile() {
        Profile p = new Profile();
        p.setLogin(login);
        p.setPassword(password);
        return p;
    }
}
