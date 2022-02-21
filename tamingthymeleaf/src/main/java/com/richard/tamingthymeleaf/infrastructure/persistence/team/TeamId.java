package com.richard.tamingthymeleaf.infrastructure.persistence.team;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class TeamId extends AbstractEntityId<UUID> {

    /**
     * Default constructor for JPA
     */
    protected TeamId() {
    }

    public TeamId(UUID id) {
        super(id);
    }
}
