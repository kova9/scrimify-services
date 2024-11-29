package com.scrimify.services.repo;

import com.scrimify.services.model.GameAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface GameAccountRepo extends JpaRepository<GameAccount, String> {
    Optional<GameAccount> findByGameIdAndInGameNameAndInGameUserId(String gameId, String inGameName, String inGameId);
}
