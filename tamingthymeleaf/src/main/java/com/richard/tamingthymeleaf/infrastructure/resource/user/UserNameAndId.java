package com.richard.tamingthymeleaf.infrastructure.resource.user;

import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserId;
import com.richard.tamingthymeleaf.infrastructure.persistence.user.UserName;

public class UserNameAndId {

    private final UserId id;
    private final UserName userName;

    public UserNameAndId(UserId id, UserName userName) {
        this.id = id;
        this.userName = userName;
    }

    public UserId getId() {
        return id;
    }

    public UserName getUserName() {
        return userName;
    }

}
