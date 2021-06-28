package com.github.igorf.shoot.web.controller.api;

import com.github.igorf.shoot.logic.auth.SecurityService;
import com.github.igorf.shoot.logic.domain.Competition;
import com.github.igorf.shoot.logic.domain.CompetitionResult;
import com.github.igorf.shoot.logic.domain.Profile;
import com.github.igorf.shoot.logic.dto.OperationResultDTO;
import com.github.igorf.shoot.logic.dto.ShotResultDTO;
import com.github.igorf.shoot.logic.service.CompetitionResultService;
import com.github.igorf.shoot.logic.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired private SecurityService securityService;
    @Autowired private CompetitionResultService competitionResultService;
    @Autowired private CompetitionService competitionService;

    @Secured("ROLE_COMPETITOR")
    @RequestMapping("/result/my/target/save/{competitionId}")
    public ResponseEntity<?> save(
            @PathVariable("competitionId") Long competitionId,
            @RequestBody ShotResultDTO[] shots) {

        Competition competition = competitionService.findById(competitionId);
        Profile profile = securityService.getLoggedProfile();
        CompetitionResult competitionResult
                = competitionResultService.findOrCreateCompetitionResult(competition, profile);
        try {
            competitionResultService.addTargetToResult(competitionResult, shots);
            return new ResponseEntity<>(OperationResultDTO.success(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(OperationResultDTO.error(), HttpStatus.BAD_REQUEST);
        }
    }
}
