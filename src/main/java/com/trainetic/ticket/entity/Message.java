package com.trainetic.ticket.entity;

import com.trainetic.auth.entity.User;
import com.trainetic.entity.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message extends Auditable {

    private String text;

    @OneToOne
    private User responder;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;
}
