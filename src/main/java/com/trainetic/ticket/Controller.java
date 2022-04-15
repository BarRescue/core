package com.trainetic.ticket;

import com.trainetic.logic.PermissionRole;
import com.trainetic.ticket.dto.TicketDTO;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@RolesAllowed({"user", "coach", "admin"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("ticket")
public class Controller {

    private final Logic logic;

    public Controller(Logic logic) {
        this.logic = logic;
    }

    @POST
    public Response createTicket(@Context SecurityContext ctx, TicketDTO dto) {
        return Response.ok(this.logic.createTicket(dto, ctx)).build();
    }

}
