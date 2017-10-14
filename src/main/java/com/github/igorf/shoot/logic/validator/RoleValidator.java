package com.github.igorf.shoot.logic.validator;

import com.github.igorf.shoot.logic.domain.Profile;
import com.github.igorf.shoot.logic.domain.Role;
import com.github.igorf.shoot.logic.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {
    @Autowired
    private RoleService roleService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Profile.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Role role = (Role) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (roleService.findByName(role.getName()) != null) {
            errors.rejectValue("name", "Duplicate.entity");
        }
    }
}
