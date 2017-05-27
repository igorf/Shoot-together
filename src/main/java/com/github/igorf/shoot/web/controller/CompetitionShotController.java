package com.github.igorf.shoot.web.controller;

import com.github.igorf.shoot.logic.domain.Shot;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompetitionShotController {

    @RequestMapping("/competition/{competitionId}/fire/{x}/{y}/{result}")
    public ResponseEntity add(@PathVariable long competitionId, @PathVariable float x, @PathVariable float y, @PathVariable float result) {
        //TODO: create shot
        Shot s = new Shot();
        return new ResponseEntity(s, HttpStatus.OK);
    }
}
