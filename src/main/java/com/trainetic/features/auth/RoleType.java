package com.trainetic.features.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleType {

    NORMAL(Values.NORMAL),
    COACH(Values.COACH),
    ORGANISATION_MANAGER(Values.ORGANISATION_MANAGER);

    private final String value;

    public static class Values {
        static final String NORMAL = "user";
        static final String COACH = "coach";
        static final String ORGANISATION_MANAGER = "organisation_manager";
    }
}
