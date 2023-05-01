package com.education.ticketsservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tickets")
@Table
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ticket_uid", columnDefinition = "UUID default gen_random_uuid()")
    private UUID ticketUid;

    @Column(name = "film_uid", nullable = false)
    private UUID filmUid;

    @Column(name = "session_uid", nullable = false)
    private UUID sessionUid;

    @Column(name = "user_name", length = 80, nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "seat_row")
    private Integer row;

    @Column(name = "seat")
    private Integer seat;

    @Column(name = "status", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public enum TicketStatus {
        BOOKED, CANCELED
    }
}
