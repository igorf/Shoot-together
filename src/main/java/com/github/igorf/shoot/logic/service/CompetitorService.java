package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.auth.SecurityService;
import com.github.igorf.shoot.logic.dao.CompetitorDao;
import com.github.igorf.shoot.logic.domain.Competitor;
import com.github.igorf.shoot.logic.domain.Profile;
import com.github.igorf.shoot.logic.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class CompetitorService {

    @Autowired private SecurityService securityService;
    @Autowired private CompetitorDao competitorDao;
    @Autowired private RoleService roleService;

    private final static Logger logger = Logger.getLogger(CompetitorService.class.getName());
    public Competitor getCurrent() {
        return competitorDao.findByProfile(securityService.getLoggedProfile());
    }

    @Transactional
    public Competitor saveCompetitor(Competitor competitor) {
        if (competitor.getId() == null) {
            Profile profile = securityService.getLoggedProfile();
            Role competitorRole = roleService.getCompetitorRole();

            if (competitorRole != null && !profile.hasRole(competitorRole.getId())) {
                profile.getRoles().add(competitorRole);
            }
            competitor.setProfile(securityService.getLoggedProfile());
        }
        try {
            return competitorDao.save(competitor);
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }
        return null;
    }

    public Competitor getByProfile(Profile profile) {
        return competitorDao.findByProfile(profile);
    }
}