package com.scrimify.services.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Team {
    @Id
    @Column(length = 36)
    private String teamId;

    @Column(length = 30)
    private String name;

    @Column(length = 36)
    private String gameId;

    @Column(length = 36)
    private String leaderId;

    private String timezone;

    @Column(name = "crttst")
    private ZonedDateTime createTimestamp;

    @Column(name = "crtuser")
    private String createUser;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<TeamMember> teamMembers;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
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
}
