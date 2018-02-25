package com.github.igorf.shoot.logic.dao;

import com.github.igorf.shoot.logic.domain.Competition;
import com.github.igorf.shoot.logic.domain.CompetitionResult;
import com.github.igorf.shoot.logic.domain.Competitor;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionResultDao extends CrudRepository<CompetitionResult, Long> {
    CompetitionResult findByCompetitionAndCompetitor(Competition competition, Competitor competitor);
}
