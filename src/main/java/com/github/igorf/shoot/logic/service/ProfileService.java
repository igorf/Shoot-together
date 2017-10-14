package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.ProfileDao;
import com.github.igorf.shoot.logic.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProfileService {
    @Autowired private ProfileDao profileDao;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    private Logger logger = Logger.getLogger(ProfileService.class.getName());

    public Profile createProfile(Profile profile) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        return profileDao.save(profile);
    }

    public Profile updateProfile(long id, Profile profile) {
        profile.setId(id);
        return profileDao.save(profile);
    }

    public Profile findByLogin(String login) {
        return profileDao.findByLogin(login);
    }
}
