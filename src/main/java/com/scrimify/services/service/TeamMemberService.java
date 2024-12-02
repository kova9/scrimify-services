package com.scrimify.services.service;

import com.scrimify.services.dto.TeamMemberDTO;
import com.scrimify.services.exception.ScrimifyException;
import com.scrimify.services.logic.TeamMemberLogic;
import com.scrimify.services.model.*;
import com.scrimify.services.model.request.AddTeamMemberRequest;
import com.scrimify.services.repo.GameAccountRepo;
import com.scrimify.services.repo.GameRepo;
import com.scrimify.services.repo.TeamMemberRepo;
import com.scrimify.services.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeamMemberService {

    @Autowired
    private TeamRepo teamRepo;
    @Autowired
    private GameAccountRepo gameAccountRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private TeamMemberLogic teamMemberLogic;
    @Autowired
    private TeamMemberRepo teamMemberRepo;

    public ResponseEntity<TeamMemberDTO> addMember(AddTeamMemberRequest req, UserPrincipal principal){

        Team team = teamRepo.findById(req.getTeamId()).orElseThrow(() -> ScrimifyException.notFound("Team not found"));
        GameAccount gameAccount = gameAccountRepo.findByGameIdAndUserId(team.getGameId(), principal.getUserId()).orElseThrow(() -> ScrimifyException.notFound("No game account"));
        Game game = gameRepo.findById(team.getGameId()).orElseThrow(() -> ScrimifyException.notFound("Game not found"));

        TeamMember teamMember = teamMemberLogic.create(team, gameAccount, game, principal.getUserId());
        try{
            teamMemberRepo.save(teamMember);
        }catch (DataIntegrityViolationException e){
            throw ScrimifyException.conflict("User is already Part of a team");
        }
        TeamMemberDTO dto = new TeamMemberDTO();
        dto.setTeamName(team.getName());
        dto.setGameName(game.getName());
        dto.setGameAccountName(gameAccount.getInGameName());

        return ResponseEntity.ok(dto);
    }
}
