package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.RoleDao;
import com.github.igorf.shoot.logic.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private static final String COMPETITOR_ROLE_NAME = "ROLE_COMPETITOR";
    @Autowired private RoleDao roleDao;

    public Role saveRole(Role role) {
        return roleDao.save(role);
    }

    public void deleteRoleByID(Long id) {
        Role role = roleDao.findById(id).orElse(null);
        if (role != null) {
            roleDao.delete(role);
        }
    }

    public Iterable<Role> findAll() {
        return roleDao.findAll();
    }

    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    public Role getCompetitorRole() {
        return findByName(COMPETITOR_ROLE_NAME);
    }
}
