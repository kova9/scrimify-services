package com.scrimify.services.service;

import com.scrimify.services.enums.MatchStatus;
import com.scrimify.services.exception.ScrimifyException;
import com.scrimify.services.logic.MatchLogic;
import com.scrimify.services.model.*;
import com.scrimify.services.model.request.MatchRequest;
import com.scrimify.services.repo.GameAccountRepo;
import com.scrimify.services.repo.MatchRepo;
import com.scrimify.services.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepo matchRepo;
    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private MatchLogic matchLogic;
    @Autowired
    private GameAccountRepo gameAccountRepo;

    public ResponseEntity<Match> create(MatchRequest req, UserPrincipal userPrincipal){
        Team contestorTeam = teamRepo.findById(req.getContestantTeamId())
                .orElseThrow(() -> ScrimifyException.notFound("Contestor team not found"));

        GameAccount gameAccount = gameAccountRepo.findByGameIdAndUserId(req.getGameId(), userPrincipal.getUserId()).orElseThrow(()-> ScrimifyException.notFound("No game Account for this game"));
        List<TeamMember> member = contestorTeam.getTeamMembers().stream().filter(teamMember -> teamMember.getGameAccount().getId().equals(gameAccount.getId())).toList();

        if (member.isEmpty()){
            throw ScrimifyException.unauthorized("Not able to create match for this team");
        }
        Match match = matchLogic.create(req, contestorTeam, userPrincipal.getUserId());
        matchRepo.save(match);

        return ResponseEntity.ok(match);
    }

    public ResponseEntity<Match> getMatchById(String gameId){
        Match match = matchRepo.findById(gameId).orElseThrow(()->ScrimifyException.notFound("Match not found"));

        return ResponseEntity.ok(match);
    }

    public ResponseEntity<List<Match>> getAllMatchesByTeamId(String teamId){
        List<Match> matches = matchRepo.findMatchesByTeamId(teamId).orElse(Collections.emptyList());

        return ResponseEntity.ok(matches);
    }

    public ResponseEntity<String> acceptMatch(String matchId, UserPrincipal principal){
        Match match = matchRepo.findById(matchId).orElseThrow(() -> ScrimifyException.notFound("Match not found"));
        GameAccount gameAccount = gameAccountRepo.findByGameIdAndUserId(match.getGameId(), principal.getUserId()).orElseThrow(()-> ScrimifyException.notFound("No game Account for this game"));
        Team defendantTeam = teamRepo.findById(match.getDefendantTeamId()).orElseThrow(() -> ScrimifyException.notFound("Defendant team not found"));
        List<TeamMember> member = defendantTeam.getTeamMembers().stream().filter(teamMember -> teamMember.getGameAccount().getId().equals(gameAccount.getId())).toList();

        if (member.isEmpty()){
            throw ScrimifyException.unauthorized("Not allowed to accept the match for this team");
        }

        match.setStatus(MatchStatus.STATUS_ACCEPTED);

        return ResponseEntity.ok("Accepted");
    }



}
