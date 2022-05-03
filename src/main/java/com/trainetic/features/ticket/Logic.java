package com.trainetic.features.ticket;

import com.trainetic.features.auth.RoleType;
import com.trainetic.features.auth.User;
import com.trainetic.features.auth.logic.UserLogic;
import com.trainetic.features.ticket.service.MessageService;
import com.trainetic.features.ticket.service.TicketService;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.SecurityContext;

@ApplicationScoped
public class Logic {

    private final TicketService ticketService;
    private final MessageService messageService;
    private final UserLogic userLogic;

    public Logic(TicketService ticketService, MessageService messageService, UserLogic userLogic) {
        this.ticketService = ticketService;
        this.messageService = messageService;
        this.userLogic = userLogic;
    }

    public Ticket createTicket(TicketDTO dto, SecurityContext ctx) {
        User creator = this.userLogic.getUser(ctx.getUserPrincipal().getName());
        User receiver = this.userLogic.getUser(dto.getReceiver());

        if(creator.getRole().getName().equals(RoleType.NORMAL.getValue())) {


        }

        if(creator.getRole().getName().equals(RoleType.COACH.getValue())) {

        }

        return new Ticket();
    }
}
