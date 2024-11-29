package com.scrimify.services.repo;

import com.scrimify.services.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, String> {

    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
}
