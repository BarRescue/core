package com.trainetic.logic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PermissionRole {

    USER(Values.USER),
    COACH(Values.COACH),
    ADMIN(Values.ADMIN),
    ORGANISATION_MANAGER(Values.ORGANISATION_MANAGER);

    private final String value;

    public static class Values {
        public static final String USER = "user";
        public static final String COACH = "coach";
        public static final String ADMIN = "admin";
        public static final String ORGANISATION_MANAGER = "organisation_manager";
    }
}
