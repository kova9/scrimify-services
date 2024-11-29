package com.scrimify.services.contoller;

import com.scrimify.services.model.GameAccount;
import com.scrimify.services.model.UserPrincipal;
import com.scrimify.services.model.request.GameAccountRequest;
import com.scrimify.services.service.GameAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gameAccount")
public class GameAccountController {

    @Autowired
    private GameAccountService service;

    @PostMapping("/")
    public ResponseEntity<GameAccount> create(@RequestBody  GameAccountRequest req, @AuthenticationPrincipal UserPrincipal principal){
        return service.create(req, principal);
    }


}
