package com.github.igorf.shoot.logic.service;

import com.github.igorf.shoot.logic.dao.TargetDao;
import com.github.igorf.shoot.logic.dao.TargetRingDao;
import com.github.igorf.shoot.logic.domain.Target;
import com.github.igorf.shoot.logic.domain.TargetRing;
import com.github.igorf.shoot.misc.RingColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TargetService {

    @Autowired private TargetDao targetDao;
    @Autowired private TargetRingDao targetRingDao;
    private Logger logger = Logger.getLogger(TargetService.class.getName());

    public Target createTarget(String title) {
        Target target = new Target();
        target.setTitle(title);
        try {
            target = targetDao.save(target);
            return target;
        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }
        return null;
    }

    public Target updateTarget(long id, String title) {
        Target target = targetDao.findById(id).orElse(null);
        if (target != null) {
            target.setTitle(title);
            try {
                target = targetDao.save(target);
                return target;
            } catch (Exception ex) {
                logger.warning(ex.getMessage());
            }
        }
        return null;
    }

    public TargetRing addRingToTarget(long targetId, int value, float diameter, RingColor color) {
        Target target = targetDao.findById(targetId).orElse(null);
        if (target != null) {
            TargetRing ring = new TargetRing();
            ring.setDenomination(value);
            ring.setDiameter(diameter);
            ring.setColor(color);
            ring.setTarget(target);

            try {
                ring = targetRingDao.save(ring);
                return ring;
            } catch (Exception ex) {
                logger.warning(ex.getMessage());
            }
        }
        return null;
    }

    public boolean removeTarget(long targetId) {
        Target target = targetDao.findById(targetId).orElse(null);
        if (target != null) {
            try {
                targetDao.delete(target);
                return true;
            } catch (Exception ex) {
                logger.warning(ex.getMessage());
            }
        }
        return false;
    }

    public Iterable<Target> listToChoice() {
        return targetDao.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }
}
