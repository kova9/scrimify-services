package com.scrimify.services.model.request;

import lombok.Data;

@Data
public class MatchRequest {

    private String contestantTeamId;
    private String defendantTeamId;
    private String matchDate;
    private String matchTime;
    private String gameId;
}
