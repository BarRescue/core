package com.trainetic.ticket.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TicketStatus {

    OPEN(Values.OPEN),
    CREATOR_REPLY(Values.CREATOR_REPLY),
    RESPONDER_REPLY(Values.RESPONDER_REPLY),
    CLOSED(Values.CLOSED);

    private final String value;

    public static class Values {
        static final String OPEN = "open";
        static final String CREATOR_REPLY = "creator_reply";
        static final String RESPONDER_REPLY = "responder_reply";
        static final String CLOSED = "closed";
    }
}
