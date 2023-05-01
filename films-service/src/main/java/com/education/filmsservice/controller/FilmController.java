package com.education.filmsservice.controller;

import com.education.filmsservice.model.FilmResponse;
import com.education.filmsservice.model.FilmsWithPaginationResponse;
import com.education.filmsservice.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/films")
public record FilmController(FilmService filmService) {

    /**
     * GET /api/v1/films : Просмотр списка всех фильмов, которые идут сегодня в кино
     *
     * @param page (optional)
     * @param size (optional)
     * @return Список всех фильмов (status code 200)
     */
    @Operation(
            operationId = "searchFilms",
            summary = "Просмотр списка всех фильмов, которые идут сегодня в кино",
            tags = {"Films Service"}
    )
    @GetMapping
    FilmsWithPaginationResponse searchFilms(@Min(1) @Valid @RequestParam int page,
                                            @Min(2) @Max(100) @Valid @RequestParam int size) {
        return filmService.getFilms(page - 1, size);
    }

    @PostMapping
    List<FilmResponse> getFilmsByUids(@RequestBody List<UUID> ids) {
        return filmService.getAll(ids);
    }
}
