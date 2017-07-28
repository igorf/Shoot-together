package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.CompetitionDao;
import com.github.igorf.shoot.logic.dao.ExerciseDao;
import com.github.igorf.shoot.logic.dao.TargetDao;
import com.github.igorf.shoot.logic.domain.Competition;
import com.github.igorf.shoot.logic.domain.Exercise;
import com.github.igorf.shoot.logic.domain.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class AdminCompetitionService {

    @Autowired private CompetitionDao competitionDao;
    @Autowired private ExerciseDao exerciseDao;
    private Logger logger = Logger.getLogger(AdminCompetitionService.class.getName());

    public Competition createCompetition(String title, Long exerciseId, Date from, Date to) {
        Competition competition = new Competition();
        Exercise exercise = exerciseDao.findOne(exerciseId);
        competition.setTitle(title);
        competition.setExercise(exercise);
        competition.setStart(from);
        competition.setEnd(to);
        try {
            competition = competitionDao.save(competition);
            return competition;
        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }
        return null;
    }

    public Competition updateCompetition(Long competitionId, String title, Long exerciseId, Date from, Date to) {
        Competition competition = competitionDao.findOne(competitionId);
        if (competition != null) {
            Exercise exercise = exerciseDao.findOne(exerciseId);
            competition.setTitle(title);
            competition.setExercise(exercise);
            competition.setStart(from);
            competition.setEnd(to);
            try {
                competition = competitionDao.save(competition);
                return competition;
            } catch (Exception ex) {
                logger.warning(ex.getMessage());
            }
        }
        return null;
    }

    public void removeCompetition(long id) {
        try {
            competitionDao.delete(id);
        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }
    }

    public Page<Competition> listPage(Pageable pageable) {
        return competitionDao.findAll(pageable);
    }

    public Competition findById(long id) {
        return competitionDao.findOne(id);
    }
}
