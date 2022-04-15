package com.trainetic.ticket;

import com.trainetic.auth.entity.RoleType;
import com.trainetic.auth.entity.User;
import com.trainetic.auth.logic.UserLogic;
import com.trainetic.ticket.dto.TicketDTO;
import com.trainetic.ticket.entity.Ticket;
import com.trainetic.ticket.service.MessageService;
import com.trainetic.ticket.service.TicketService;

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
    }
}
