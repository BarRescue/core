package com.trainetic.logic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PermissionRole {

    USER(Values.USER),
    COACH(Values.COACH),
    ADMIN(Values.ADMIN);

    private final String value;

    public static class Values {
        static final String USER = "user";
        static final String COACH = "coach";
        static final String ADMIN = "admin";
    }
}
