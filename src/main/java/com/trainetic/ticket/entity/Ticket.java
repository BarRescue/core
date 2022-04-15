package com.trainetic.ticket.entity;

import com.trainetic.auth.entity.User;
import com.trainetic.entity.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends Auditable {

    @OneToOne
    private User creator;

    @OneToOne
    private User receiver;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @OneToMany(mappedBy = "ticket")
    private Set<Message> messages;

}
