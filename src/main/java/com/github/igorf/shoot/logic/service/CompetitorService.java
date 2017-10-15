package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.auth.SecurityService;
import com.github.igorf.shoot.logic.dao.CompetitorDao;
import com.github.igorf.shoot.logic.domain.Competitor;
import com.github.igorf.shoot.logic.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitorService {

    @Autowired private SecurityService securityService;
    @Autowired private CompetitorDao competitorDao;

    public Competitor getCurrent() {
        Profile currentProfile = securityService.getLoggedProfile();
        Competitor c = competitorDao.findByProfile(currentProfile);
        if (c == null) {
            c = new Competitor();
            c.setProfile(currentProfile);
        }
        return c;
    }

    public Competitor saveCompetitor(Competitor competitor) {
        if (competitor.getId() == null) {
            competitor.setProfile(securityService.getLoggedProfile());
        }
        return competitorDao.save(competitor);
    }
}