package com.richard.tamingthymeleaf.infrastructure.persistence.team;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface TeamRepository extends CrudRepository<Team, TeamId>, TeamRepositoryCustom {
    @Query("SELECT new com.richard.tamingthymeleaf.infrastructure.persistence.team.TeamSummary(t.id, t.name, t.coach.id, t.coach.userName) FROM Team t")
    Page<TeamSummary> findAllSummary(Pageable pageable);

    @Query("FROM Team t JOIN FETCH t.players WHERE t.id = :id")
    Optional<Team> findTeamWithPlayers(@Param("id") TeamId id);

    boolean existsByName(String name);
}
