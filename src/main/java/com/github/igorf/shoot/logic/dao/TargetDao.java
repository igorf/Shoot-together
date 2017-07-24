package com.github.igorf.shoot.logic.dao;

import com.github.igorf.shoot.logic.domain.Target;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TargetDao extends PagingAndSortingRepository<Target, Long> {}

