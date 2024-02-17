package com.example.implementation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    private long id;
    private long userId;
    private long eventId;
    private int seat;

    public Ticket(long id, long userId, long eventId, int seat) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", seat=" + seat +
                '}';
    }
}
