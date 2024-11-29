package com.scrimify.services.contoller;

import com.scrimify.services.model.Team;
import com.scrimify.services.model.UserPrincipal;
import com.scrimify.services.model.request.TeamRequest;
import com.scrimify.services.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    TeamService service;

    @PostMapping("/")
    public ResponseEntity<Team> createTeam(@RequestBody TeamRequest req, @AuthenticationPrincipal UserPrincipal context){
        return ResponseEntity.ok(service.create(req, context));
    }
}
