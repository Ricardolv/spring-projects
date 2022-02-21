package com.richard.tamingthymeleaf.infrastructure.persistence.team;

public interface TeamRepositoryCustom {

    TeamId nextId();
    TeamPlayerId nextPlayerId();

}
