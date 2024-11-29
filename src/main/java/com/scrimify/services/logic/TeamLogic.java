package com.scrimify.services.logic;

import com.scrimify.services.model.Team;
import com.scrimify.services.model.request.TeamRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeamLogic {

    public Team create(TeamRequest req, String userId){
        Team team = new Team();

        team.setTeamId(UUID.randomUUID().toString());
        team.setName(req.getName());
        team.setGameId(req.getGameId());
        team.setLeaderId(userId);

        return team;
    }
}
