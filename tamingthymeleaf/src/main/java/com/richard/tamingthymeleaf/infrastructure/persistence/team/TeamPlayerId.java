package com.richard.tamingthymeleaf.infrastructure.persistence.team;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class TeamPlayerId extends AbstractEntityId<UUID> {

    /**
     * Default constructor for JPA
     */
    protected TeamPlayerId() {
    }

    public TeamPlayerId(UUID id) {
        super(id);
    }
}
