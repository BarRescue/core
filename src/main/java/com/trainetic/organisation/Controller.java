package com.trainetic.organisation;

import com.trainetic.auth.logic.UserLogic;
import com.trainetic.dto.UserDTO;
import com.trainetic.entity.RoleType;
import com.trainetic.logic.PermissionRole;
import io.quarkus.logging.Log;
import io.quarkus.security.jpa.Roles;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("organisation")
public class Controller {

    private final UserLogic userLogic;
    private final Logic organisationLogic;

    public Controller(UserLogic userLogic, Logic organisationLogic) {
        this.organisationLogic = organisationLogic;
        this.userLogic = userLogic;
    }

    @Path("/add/user")
    @POST
    @RolesAllowed({PermissionRole.Values.ORGANISATION_MANAGER, PermissionRole.Values.COACH})
    public Response addUserToOrganisation(@Context SecurityContext cx, UserDTO dto) {
        return Response.ok(this.userLogic.createUserForOrganisation(dto, cx.getUserPrincipal().getName())).build();
    }
}
