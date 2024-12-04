package com.scrimify.services.repo;

import com.scrimify.services.model.Game;
import com.scrimify.services.model.GameAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface GameAccountRepo extends JpaRepository<GameAccount, String> {
    Optional<GameAccount> findByGameIdAndInGameNameAndInGameUserId(String gameId, String inGameName, String inGameId);
    Optional<GameAccount> findByGameIdAndUserId(String gameId, String userId);
    Optional<List<GameAccount>> findByUserId(String userId);
}
