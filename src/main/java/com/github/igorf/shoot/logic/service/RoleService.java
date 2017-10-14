package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.RoleDao;
import com.github.igorf.shoot.logic.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RoleService {
    @Autowired private RoleDao roleDao;

    private Logger logger = Logger.getLogger(RoleService.class.getName());

    public Role createRole(Role role) {
        return roleDao.save(role);
    }

    public Iterable<Role> findAll() {
        return roleDao.findAll();
    }
}
