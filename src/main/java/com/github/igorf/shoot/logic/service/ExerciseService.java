package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.ExerciseDao;
import com.github.igorf.shoot.logic.dao.TargetDao;
import com.github.igorf.shoot.logic.domain.Exercise;
import com.github.igorf.shoot.logic.domain.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ExerciseService {

    @Autowired private TargetDao targetDao;
    @Autowired private ExerciseDao exerciseDao;
    private Logger logger = Logger.getLogger(ExerciseService.class.getName());

    public Exercise createExercise(
            String title,
            String description,
            int shots,
            int timeLimit,
            float caliber,
            float distance,
            long targetId
    ) {
        Target target = targetDao.findOne(targetId);
        Exercise exercise = new Exercise();
        exercise.setTitle(title);
        exercise.setDescription(description);
        exercise.setShots(shots);
        exercise.setTimeLimit(timeLimit);
        exercise.setCaliber(caliber);
        exercise.setDistance(distance);
        exercise.setTarget(target);
        try {
            exercise = exerciseDao.save(exercise);
            return exercise;
        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }
        return null;
    }

    public Exercise updateExercise(
            long id,
            String title,
            String description,
            int shots,
            int timeLimit,
            float caliber,
            float distance,
            long targetId
    ) {
        Target target = targetDao.findOne(targetId);
        Exercise exercise = exerciseDao.findOne(id);
        if (exercise != null) {
            exercise.setTitle(title);
            exercise.setDescription(description);
            exercise.setShots(shots);
            exercise.setTimeLimit(timeLimit);
            exercise.setCaliber(caliber);
            exercise.setDistance(distance);
            exercise.setTarget(target);
            try {
                exercise = exerciseDao.save(exercise);
                return exercise;
            } catch (Exception ex) {
                logger.warning(ex.getMessage());
            }
        }
        return null;
    }

    public void removeExercise(long id) {
        try {
            exerciseDao.delete(id);
        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }
    }

    public Page<Exercise> listPage(Pageable pageable) {
        return exerciseDao.findAll(pageable);
    }

    public Exercise findById(long id) {
        return exerciseDao.findOne(id);
    }
}
