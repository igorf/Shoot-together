package com.github.igorf.shoot.logic.validator;

import com.github.igorf.shoot.logic.domain.Profile;
import com.github.igorf.shoot.logic.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProfileValidator implements Validator {
    @Autowired
    private ProfileService profileService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Profile.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Profile profile = (Profile) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty");

        if (profile.getLogin().length() < 4 || profile.getLogin().length() > 32) {
            errors.rejectValue("login", "Size.userForm.login");
        }
        if (profileService.findByLogin(profile.getLogin()) != null) {
            errors.rejectValue("login", "Duplicate.userForm.login");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (profile.getPassword().length() < 6 || profile.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!profile.getPasswordConfirm().equals(profile.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
