package com.scrimify.services.logic;

import com.scrimify.services.model.GameAccount;
import com.scrimify.services.model.Users;
import com.scrimify.services.model.request.GameAccountRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GameAccountLogic {

    public GameAccount create(GameAccountRequest req, Users user){
        GameAccount gameAccount = new GameAccount();

        gameAccount.setId(UUID.randomUUID().toString());
        gameAccount.setGameId(req.getGameId());
        gameAccount.setInGameName(req.getInGameName());
        gameAccount.setInGameUserId(req.getInGameId());
        gameAccount.setUserId(user.getId());

        return gameAccount;
    }
}
