package com.scrimify.services.service;

import com.scrimify.services.exception.ScrimifyException;
import com.scrimify.services.logic.TeamLogic;
import com.scrimify.services.model.Team;
import com.scrimify.services.model.UserPrincipal;
import com.scrimify.services.model.request.TeamRequest;
import com.scrimify.services.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private TeamLogic teamLogic;


    public ResponseEntity<Team> create(TeamRequest req, UserPrincipal context) {
        teamRepo.getTeamByNameAndGameId(req.getName(), req.getGameId()).ifPresent( team -> {
            throw ScrimifyException.conflict("Team already exists for this game");
        });

        Team team = teamLogic.create(req, context.getUserId());
        teamRepo.save(team);

        return ResponseEntity.ok(team);
    }

}
