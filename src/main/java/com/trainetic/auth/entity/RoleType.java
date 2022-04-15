package com.trainetic.auth.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleType {

    NORMAL(Values.NORMAL),
    COACH(Values.COACH);

    private final String value;

    public static class Values {
        static final String NORMAL = "user";
        static final String COACH = "coach";
    }
}
