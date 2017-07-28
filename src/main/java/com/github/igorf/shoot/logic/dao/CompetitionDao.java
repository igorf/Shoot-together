package com.github.igorf.shoot.logic.dao;

import com.github.igorf.shoot.logic.domain.Competition;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompetitionDao extends PagingAndSortingRepository<Competition, Long> {}
