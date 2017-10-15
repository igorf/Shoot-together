package com.github.igorf.shoot.logic.dao;

import com.github.igorf.shoot.logic.domain.Competitor;
import com.github.igorf.shoot.logic.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface CompetitorDao extends CrudRepository<Competitor, Long> {
    Competitor findByProfile(Profile profile);
}
