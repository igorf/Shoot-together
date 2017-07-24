package com.github.igorf.shoot.logic.dao;

import com.github.igorf.shoot.logic.domain.Exercise;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExerciseDao extends PagingAndSortingRepository<Exercise, Long> {}
