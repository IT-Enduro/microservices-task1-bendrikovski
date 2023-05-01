package com.education.filmsservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class FilmResponse {
    @Valid
    @Schema(name = "filmUid", description = "UID фильма")
    private UUID filmUid;

    @Schema(name = "name", description = "Название фильма")
    private String name;

    @Schema(name = "rating", description = "Рейтинг фильма на IMDB")
    private BigDecimal rating;

    @Schema(name = "director", description = "Режиссер")
    private String director;

    @Schema(name = "producer", description = "Продюссер")
    private String producer;

    @Schema(name = "genre", description = "Жанр")
    private String genre;
}

