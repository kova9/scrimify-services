package com.scrimify.services.repo;

import com.scrimify.services.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepo extends JpaRepository<TeamMember, String> {
}
