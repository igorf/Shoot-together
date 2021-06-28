package com.github.igorf.shoot.web.controller.admin;

import com.github.igorf.shoot.logic.dao.TargetDao;
import com.github.igorf.shoot.logic.dao.TargetRingDao;
import com.github.igorf.shoot.logic.domain.Target;
import com.github.igorf.shoot.logic.domain.TargetRing;
import com.github.igorf.shoot.logic.service.TargetService;
import com.github.igorf.shoot.misc.RingColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/admin/ring")
@Secured("ROLE_ADMIN")
public class TargetRingController {
    @Autowired private TargetRingDao targetRingDao;
    @Autowired private TargetDao targetDao;
    @Autowired private TargetService targetService;
    private Logger logger = Logger.getLogger(TargetRingController.class.getName());

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        try {
            TargetRing ring = targetRingDao.findById(id).orElse(null);
            if (ring != null) {
                long targetId = ring.getTarget().getId();
                targetRingDao.delete(ring);
                return "redirect:/admin/target/view/" + targetId;
            }
        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }
        return "redirect:/admin/target/list";
    }

    @RequestMapping("/create/{targetId}")
    public String create(Model model, @PathVariable("targetId") long targetId) {
        model.addAttribute("target", targetDao.findById(targetId).orElse(null));
        return "ring/edit";
    }

    @RequestMapping("/view/{id}")
    public String update(Model model, @PathVariable("id") long id) {
        TargetRing ring = targetRingDao.findById(id).orElse(null);
        model.addAttribute("target", ring.getTarget());
        model.addAttribute("ring", ring);
        return "ring/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String edit(
            @RequestParam(value = "targetId", required = false, defaultValue = "-1") long targetId,
            @RequestParam(value = "ringId", required = false, defaultValue = "-1") long ringId,
            @RequestParam("diameter") float diameter,
            @RequestParam("denomination") int denomination,
            @RequestParam("color") int color) {

        TargetRing ring;
        Target target;

        if (ringId == -1) {
            target = targetDao.findById(targetId).orElse(null);
            targetService.addRingToTarget(targetId, denomination, diameter, RingColor.values()[color]);
        } else {
            ring = targetRingDao.findById(ringId).orElse(null);
            ring.setDenomination(denomination);
            ring.setDiameter(diameter);
            ring.setColor(RingColor.values()[color]);
            ring = targetRingDao.save(ring);
            target = ring.getTarget();
        }

        return "redirect:/admin/target/view/" + target.getId();
    }
}
