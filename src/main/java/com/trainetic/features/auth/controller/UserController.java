package com.trainetic.features.auth.controller;


import com.trainetic.features.auth.logic.UserLogic;
import com.trainetic.features.auth.InitialUserDTO;
import com.trainetic.features.auth.UserDTO;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    private final UserLogic userLogic;

    public UserController(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    @POST
    @Path("register")
    @PermitAll
    public Response register(InitialUserDTO dto) {
        return Response.ok(userLogic.createInitialUser(dto)).build();
    }

    @POST
    @Path("login")
    @PermitAll
    public Response login(UserDTO dto) {
        try {
            return Response.ok(userLogic.loginUser(dto)).build();
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("profile")
    public Response profile(@Context SecurityContext ctx) {
        try {
            return Response.ok(userLogic.getUser(ctx.getUserPrincipal().getName())).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
