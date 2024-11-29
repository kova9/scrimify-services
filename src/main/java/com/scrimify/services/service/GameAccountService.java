package com.scrimify.services.service;

import com.scrimify.services.exception.ScrimifyException;
import com.scrimify.services.logic.GameAccountLogic;
import com.scrimify.services.model.GameAccount;
import com.scrimify.services.model.UserPrincipal;
import com.scrimify.services.model.request.GameAccountRequest;
import com.scrimify.services.repo.GameAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameAccountService {

    @Autowired
    private GameAccountRepo gameAccountRepo;
    @Autowired
    private GameAccountLogic gameAccountLogic;

    public ResponseEntity<GameAccount> create(GameAccountRequest req, UserPrincipal principal){
        gameAccountRepo.findByGameIdAndInGameNameAndInGameUserId(req.getGameId(), req.getInGameName(), req.getInGameId()).ifPresent(acc ->
                {
                    throw ScrimifyException.conflict("In Game Name and ID combination already exists for this game");
                }
        );

        GameAccount gameAccount = gameAccountLogic.create(req, principal.getUser());
        gameAccountRepo.save(gameAccount);

        return ResponseEntity.ok(gameAccount);
    }
}
