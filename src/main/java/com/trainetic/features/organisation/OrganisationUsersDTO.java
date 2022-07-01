package com.trainetic.features.organisation;

import com.trainetic.features.auth.User;
import lombok.Builder;

import java.util.List;

@Builder
public class OrganisationUsersDTO {

    private List<User> users;

}
