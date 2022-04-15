package com.trainetic.ticket.service;

import com.trainetic.ticket.repository.MessageRepository;
import com.trainetic.ticket.repository.TicketRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

}
