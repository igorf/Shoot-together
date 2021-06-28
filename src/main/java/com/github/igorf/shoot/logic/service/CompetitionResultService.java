package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.CompetitionResultDao;
import com.github.igorf.shoot.logic.dao.CompetitorDao;
import com.github.igorf.shoot.logic.dao.ExerciseDao;
import com.github.igorf.shoot.logic.domain.*;
import com.github.igorf.shoot.logic.dto.ShotResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    @Transactional
    public CompetitorTarget addTargetToResult(CompetitionResult result, ShotResultDTO[] shots) {
        if (!result.needMoreTargets() || !result.isEditable()) {
            throw new IllegalArgumentException();
        }
        Date now = new Date();
        CompetitorTarget target = new CompetitorTarget();
        target.setDateCreated(now);
        target.setCompetitionResult(result);
        List<Shot> shotList = new ArrayList<>(shots.length);
        for (ShotResultDTO r: shots) {
            Shot s = new Shot();
            s.setResult(r.getValue());
            s.setX(r.getX());
            s.setY(r.getY());
            s.setCompetitorTarget(target);
            s.setDateCreated(now);
            shotList.add(s);
        }
        target.setShots(shotList);
        result.addTarget(target);
        result.setResult(result.getResult() + target.getScore());
        competitionResultDao.save(result);

        return target;
    }

    @Transactional
    @Secured("ROLE_COMPETITOR")
    public CompetitionResult publish(CompetitionResult result) {
        if (result.isSendable()) {
            result.setSent(true);
            competitionResultDao.save(result);
        }
        return result;
    }

    public Collection<CompetitionResult> getVisibleResults(Competition competition) {
        return competitionResultDao.findAllByCompetitionAndSentOrderByResultDesc(competition, true);
    }

    public CompetitionResult getByID(Long id) {
        return competitionResultDao.findOne(id);
    }
}