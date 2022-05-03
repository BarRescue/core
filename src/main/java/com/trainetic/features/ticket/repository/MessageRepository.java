package com.trainetic.features.ticket.repository;

import com.trainetic.features.ticket.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}
