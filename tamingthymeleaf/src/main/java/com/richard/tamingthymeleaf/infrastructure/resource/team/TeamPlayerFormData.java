package com.richard.tamingthymeleaf.infrastructure.resource.team;

import com.richard.tamingthymeleaf.infrastructure.persistence.team.PlayerPosition;
import com.richard.tamingthymeleaf.infrastructure.persistence.team.TeamPlayer;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserId;

import javax.validation.constraints.NotNull;

public class TeamPlayerFormData {

    @NotNull
    private UserId playerId;

    @NotNull
    private PlayerPosition position;

    public UserId getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UserId playerId) {
        this.playerId = playerId;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public static TeamPlayerFormData fromTeamPlayer(TeamPlayer player) {
        TeamPlayerFormData result = new TeamPlayerFormData();
        result.setPlayerId(player.getPlayer().getId());
        result.setPosition(player.getPosition());
        return result;
    }

}
