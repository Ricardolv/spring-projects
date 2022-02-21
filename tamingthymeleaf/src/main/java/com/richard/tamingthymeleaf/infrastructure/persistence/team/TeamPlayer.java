package com.richard.tamingthymeleaf.infrastructure.persistence.team;

import com.richard.tamingthymeleaf.infrastructure.persistence.user.User;
import io.github.wimdeblauwe.jpearl.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class TeamPlayer extends AbstractEntity<TeamPlayerId> {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Team team;

    @OneToOne
    @NotNull
    private User player;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PlayerPosition position;

    protected TeamPlayer() {
    }

    public TeamPlayer(TeamPlayerId id,
                      User player,
                      PlayerPosition position) {
        super(id);
        this.player = player;
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getPlayer() {
        return player;
    }

    public PlayerPosition getPosition() {
        return position;
    }
}
