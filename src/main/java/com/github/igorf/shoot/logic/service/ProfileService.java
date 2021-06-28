package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.ProfileDao;
import com.github.igorf.shoot.logic.domain.Competitor;
import com.github.igorf.shoot.logic.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
    @Autowired private ProfileDao profileDao;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private CompetitorService competitorService;

    public Profile saveProfile(Profile profile) {
        if (profile.getId() == null) {
            profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        } else {
            profile.setPassword(findByID(profile.getId()).getPassword());
        }
        return profileDao.save(profile);
    }

    public Profile findByLogin(String login) {
        return profileDao.findByLogin(login);
    }

    public Profile findByID(Long id) {
        return profileDao.findById(id).orElse(null);
    }

    @Transactional
    public void deleteProfileByID(Long id) {
        if (id != null) {
            Profile p = findByID(id);
            Competitor c = competitorService.getByProfile(p);
            if (c != null) {
                c.setProfile(null);
            }
            profileDao.delete(p);
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
