package com.scrimify.services.model.request;

import lombok.Data;

@Data
public class GameAccountRequest {
    private String inGameName;
    private String inGameId;
    private String gameId;
}
