package com.richard.tamingthymeleaf.domain.team;


import com.richard.tamingthymeleaf.infrastructure.persistence.team.PlayerPosition;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserId;

public class TeamPlayerParameters {

    private final UserId playerId;
    private final PlayerPosition position;

    public TeamPlayerParameters(UserId playerId, PlayerPosition position) {
        this.playerId = playerId;
        this.position = position;
    }

    public UserId getPlayerId() {
        return playerId;
    }

    public PlayerPosition getPosition() {
        return position;
    }
}
