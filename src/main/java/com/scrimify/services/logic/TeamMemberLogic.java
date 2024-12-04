package com.scrimify.services.logic;

import com.scrimify.services.model.Game;
import com.scrimify.services.model.GameAccount;
import com.scrimify.services.model.Team;
import com.scrimify.services.model.TeamMember;
import com.scrimify.services.util.TimestampUtil;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeamMemberLogic {

    public TeamMember create(Team team, GameAccount gameAccount, Game game, String userId){
        TeamMember teamMember = new TeamMember();

        teamMember.setId(UUID.randomUUID().toString());
        teamMember.setTeam(team);
        teamMember.setGame(game);
        teamMember.setGameAccount(gameAccount);
        teamMember.setCreateTimestamp(TimestampUtil.now());

        return teamMember;
    }
}
