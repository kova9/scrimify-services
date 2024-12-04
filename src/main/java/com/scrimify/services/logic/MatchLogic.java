package com.scrimify.services.logic;

import com.scrimify.services.enums.MatchStatus;
import com.scrimify.services.model.Match;
import com.scrimify.services.model.Team;
import com.scrimify.services.model.request.MatchRequest;
import com.scrimify.services.util.TimestampUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Component
public class MatchLogic {

    public Match create(MatchRequest req, Team contestorTeam, String userId){
        Match match = new Match();

        ZoneId contestorTimezone = ZoneId.of(contestorTeam.getTimezone());
        ZonedDateTime localMatchTime = ZonedDateTime.of(
                LocalDate.parse(req.getMatchDate()),
                LocalTime.parse(req.getMatchTime()),
                contestorTimezone
        );

        match.setMatchDateTime(localMatchTime.withZoneSameInstant(ZoneId.of("Europe/Berlin")));
        match.setId(UUID.randomUUID().toString());
        match.setContestorTeamId(req.getContestantTeamId());
        match.setDefendantTeamId(req.getDefendantTeamId());
        match.setGameId(req.getGameId());
        match.setCreateUser(userId);
        match.setCreateTimestamp(TimestampUtil.now());
        match.setStatus(MatchStatus.STATUS_INITIATED);

        return match;
    }
}
