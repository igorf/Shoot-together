package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.RoleDao;
import com.github.igorf.shoot.logic.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired private RoleDao roleDao;

    public Role saveRole(Role role) {
        return roleDao.save(role);
    }

    public void deleteRoleByID(Long id) {
        if (id != null) {
            roleDao.delete(id);
        }
    }

    public Iterable<Role> findAll() {
        return roleDao.findAll();
    }

    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
