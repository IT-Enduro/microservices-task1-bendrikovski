package com.education.filmsservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "films")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "film_uid", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID filmUid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rating")
    @Size(max = 10)
    private BigDecimal rating;

    @Column(name = "director")
    private String director;

    @Column(name = "producer")
    private String producer;

    @Column(name = "genre", nullable = false)
    private String genre;


}
