package com.trainetic.features.ticket.service;

import com.trainetic.features.ticket.repository.MessageRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

}
