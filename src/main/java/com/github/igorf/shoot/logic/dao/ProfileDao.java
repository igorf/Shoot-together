package com.github.igorf.shoot.logic.dao;

import com.github.igorf.shoot.logic.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileDao extends CrudRepository<Profile, Long> {
    Profile findByLogin(String login);
}
