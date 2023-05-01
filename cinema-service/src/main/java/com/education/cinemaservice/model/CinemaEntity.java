package com.education.cinemaservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "cinema")
public class CinemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cinema_uid", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID cinemaUid;

    @Column
    private String name;

    @Column
    private String address;
}
