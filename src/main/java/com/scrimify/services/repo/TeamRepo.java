package com.scrimify.services.repo;

import com.scrimify.services.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepo extends JpaRepository<Team, String> {
    Optional<Team> getTeamByNameAndGameId(String name, String gameId);
}
