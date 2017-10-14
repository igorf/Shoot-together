package com.github.igorf.shoot.logic.dao;

import com.github.igorf.shoot.logic.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Long> {
}
