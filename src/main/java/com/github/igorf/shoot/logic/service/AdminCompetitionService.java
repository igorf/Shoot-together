package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.CompetitionDao;
import com.github.igorf.shoot.logic.dao.ExerciseDao;
import com.github.igorf.shoot.logic.domain.Competition;
import com.github.igorf.shoot.logic.domain.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminCompetitionService {

    @Autowired private CompetitionDao competitionDao;
    @Autowired private ExerciseDao exerciseDao;

    public Competition createCompetition(String title, Long exerciseId, Date from, Date to) throws Exception {
        Competition competition = new Competition();
        Exercise exercise = exerciseDao.findOne(exerciseId);
        competition.setTitle(title);
        competition.setExercise(exercise);
        competition.setStart(from);
        competition.setEnd(to);
        checkCompetitionBeforeSave(competition);
        competition = competitionDao.save(competition);
        return competition;
    }

    public Competition updateCompetition(Long competitionId, String title, Long exerciseId, Date from, Date to) throws Exception {
        Competition competition = competitionDao.findOne(competitionId);
        if (competition == null) {
            throw new Exception("Competition with ID " + competitionId + " not found");
        }
        Exercise exercise = exerciseDao.findOne(exerciseId);
        competition.setTitle(title);
        competition.setExercise(exercise);
        competition.setStart(from);
        competition.setEnd(to);
        checkCompetitionBeforeSave(competition);
        competition = competitionDao.save(competition);
        return competition;
    }

    public void removeCompetition(long id) throws Exception {
        competitionDao.delete(id);
    }

    public Page<Competition> listPage(Pageable pageable) {
        return competitionDao.findAll(pageable);
    }

    public Competition findById(long id) {
        return competitionDao.findOne(id);
    }

    private static void checkCompetitionBeforeSave(Competition competition) throws Exception {
        Date today = new Date();

        if (competition.getEnd().getTime() < today.getTime()) {
            throw new Exception("It is not so good idea to create competition in the past");
        }
        if (competition.getEnd().getTime() < competition.getStart().getTime()) {
            throw new Exception("Competition must have at least 1 day long");
        }
    }
}
