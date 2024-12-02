package com.scrimify.services.contoller;

import com.scrimify.services.dto.TeamMemberDTO;
import com.scrimify.services.model.UserPrincipal;
import com.scrimify.services.model.request.AddTeamMemberRequest;
import com.scrimify.services.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team/member")
public class TeamMemberController {

    @Autowired
    private TeamMemberService service;

    @PostMapping("/")
    public ResponseEntity<TeamMemberDTO> addTeamMember(@RequestBody AddTeamMemberRequest req, @AuthenticationPrincipal UserPrincipal principal){
        return service.addMember(req, principal);
    }
}
