package com.trainetic.features.ticket.service;

import com.trainetic.features.ticket.repository.TicketRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

}
