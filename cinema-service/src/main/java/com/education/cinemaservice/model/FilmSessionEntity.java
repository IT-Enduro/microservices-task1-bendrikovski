package com.education.cinemaservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "film_sessions")
public class FilmSessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "session_uid", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID sessionUid;

    @Column(name = "film_uid", nullable = false)
    private UUID filmUid;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "booked_seats", nullable = false)
    private Integer bookedSeats;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "cinema_uid")
    private UUID cinemaUid;

}
