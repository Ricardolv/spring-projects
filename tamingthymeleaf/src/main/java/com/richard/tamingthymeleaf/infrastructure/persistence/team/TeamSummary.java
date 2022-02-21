package com.richard.tamingthymeleaf.infrastructure.persistence.team;

import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserId;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserName;

public class TeamSummary {

    private final TeamId id;
    private final String name;
    private final UserId coachId;
    private final UserName coachName;

    public TeamSummary(TeamId id, String name, UserId coachId, UserName coachName) {
        this.id = id;
        this.name = name;
        this.coachId = coachId;
        this.coachName = coachName;
    }

    public TeamId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserId getCoachId() {
        return coachId;
    }

    public UserName getCoachName() {
        return coachName;
    }

}
