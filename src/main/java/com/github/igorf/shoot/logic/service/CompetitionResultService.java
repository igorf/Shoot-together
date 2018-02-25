package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.CompetitionResultDao;
import com.github.igorf.shoot.logic.dao.CompetitorDao;
import com.github.igorf.shoot.logic.dao.ExerciseDao;
import com.github.igorf.shoot.logic.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompetitionResultService {

    @Autowired private CompetitionResultDao competitionResultDao;
    @Autowired private CompetitorDao competitorDao;

    public CompetitionResult findOrCreateCompetitionResult(Competition competition, Profile profile) {
        Competitor competitor = competitorDao.findByProfile(profile);
        CompetitionResult result = competitionResultDao.findByCompetitionAndCompetitor(competition, competitor);
        if (result == null) {
            result = new CompetitionResult();
            result.setCompetition(competition);
            result.setCompetitor(competitor);
        }

        return result;
    }
}