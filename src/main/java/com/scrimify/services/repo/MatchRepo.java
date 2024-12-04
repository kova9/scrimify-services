package com.scrimify.services.repo;

import com.scrimify.services.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepo extends JpaRepository<Match, String> {
    @Query("SELECT m FROM Match m WHERE m.contestorTeamId = :teamId OR m.defendantTeamId = :teamId")
    Optional<List<Match>> findMatchesByTeamId(@Param("teamId") String teamId);
}
