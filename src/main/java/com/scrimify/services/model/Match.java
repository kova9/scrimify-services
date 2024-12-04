package com.scrimify.services.model;

import com.scrimify.services.enums.MatchStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class Match {

    @Id
    @Column(length = 36)
    private String id;

    @Column(length = 36)
    private String gameId;

    @Column(length = 36)
    private String contestorTeamId;

    @Column(length = 36)
    private String defendantTeamId;

    @Column
    private MatchStatus status;
    @Column(name = "matchtst")
    private ZonedDateTime matchDateTime;

    @Column(name = "crttst")
    private ZonedDateTime createTimestamp;

    @Column(name = "crtuser")
    private String createUser;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getContestorTeamId() {
        return contestorTeamId;
    }

    public void setContestorTeamId(String contestorTeamId) {
        this.contestorTeamId = contestorTeamId;
    }

    public String getDefendantTeamId() {
        return defendantTeamId;
    }

    public void setDefendantTeamId(String defendantTeamId) {
        this.defendantTeamId = defendantTeamId;
    }

    public ZonedDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public void setMatchDateTime(ZonedDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }

    public ZonedDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(ZonedDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

}
