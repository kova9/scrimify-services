package com.scrimify.services.contoller;

import com.scrimify.services.model.Match;
import com.scrimify.services.model.UserPrincipal;
import com.scrimify.services.model.request.MatchRequest;
import com.scrimify.services.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService service;

    @PostMapping("/")
    public ResponseEntity<Match> create(@RequestBody MatchRequest req, @AuthenticationPrincipal UserPrincipal principal){
        return service.create(req,principal);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Match> getById(@PathVariable("gameId") String gameId){
        return service.getMatchById(gameId);
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Match>> getByTeamId(@PathVariable("teamId") String teamId){
        return service.getAllMatchesByTeamId(teamId);
    }

    @PostMapping("/accept/{matchId}")
    public ResponseEntity<String> acceptMatch(@PathVariable("matchId") String matchId, @AuthenticationPrincipal UserPrincipal principal){
        return service.acceptMatch(matchId, principal);
    }
}
