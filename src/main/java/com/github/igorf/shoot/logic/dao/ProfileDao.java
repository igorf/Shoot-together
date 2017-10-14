package com.github.igorf.shoot.logic.dao;

import com.github.igorf.shoot.logic.domain.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileDao extends PagingAndSortingRepository<Profile, Long> {
    Profile findByLogin(String login);
}
