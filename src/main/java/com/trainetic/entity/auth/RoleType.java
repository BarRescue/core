package com.trainetic.entity.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleType {

    NORMAL(Values.NORMAL),
    COACH(Values.COACH);

    private final String value;

    public static class Values {
        static final String NORMAL = "NORMAL";
        static final String COACH = "COACH";
    }
}
