package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.ProfileDao;
import com.github.igorf.shoot.logic.domain.Competition;
import com.github.igorf.shoot.logic.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired private ProfileDao profileDao;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    public Profile saveProfile(Profile profile) {
        if (profile.getId() == null) {
            profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        }
        return profileDao.save(profile);
    }

    public Profile findByLogin(String login) {
        return profileDao.findByLogin(login);
    }

    public Profile findByID(Long id) {
        return profileDao.findOne(id);
    }

    public void deleteProfileByID(Long id) {
        if (id != null) {
            profileDao.delete(id);
        }
    }

    public Page<Profile> getPage(Pageable pageable) {
        return profileDao.findAll(pageable);
    }

    public Profile updatePassword(Profile profile) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        return profileDao.save(profile);
    }
}
