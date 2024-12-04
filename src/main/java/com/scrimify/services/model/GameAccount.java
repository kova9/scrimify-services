package com.scrimify.services.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
public class GameAccount {
    @Id
    @Column(length = 36)
    private String id;

    private String inGameName;
    private String inGameUserId;

    @Column(length = 36)
    private String userId;

    @Column
    private String gameId;

    @Column(name = "crttst")
    private ZonedDateTime createTimestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInGameName() {
        return inGameName;
    }

    public void setInGameName(String inGameName) {
        this.inGameName = inGameName;
    }

    public String getInGameUserId() {
        return inGameUserId;
    }

    public void setInGameUserId(String inGameUserId) {
        this.inGameUserId = inGameUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public ZonedDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(ZonedDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
