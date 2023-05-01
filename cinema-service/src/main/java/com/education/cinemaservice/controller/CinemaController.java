package com.education.cinemaservice.controller;

import com.education.cinemaservice.model.CinemaWithPaginationResponse;
import com.education.cinemaservice.model.ErrorResponse;
import com.education.cinemaservice.model.FilmsInCinemaResponse;
import com.education.cinemaservice.service.CinemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cinema")
public record CinemaController(CinemaService cinemaService) {

    /**
     * GET /api/v1/cinema : Просмотр списка всех кинотеатров
     *
     * @param page (optional)
     * @param size (optional)
     * @return Список фильмов в выбранном кинотеатре (status code 200)
     */
    @Operation(summary = "Просмотр списка всех кинотеатров", tags = {"Cinema Service"})
    @GetMapping
    CinemaWithPaginationResponse getAllCinemas(
            @Min(1) @Valid @RequestParam int page,
            @Min(2) @Max(100) @Valid @RequestParam int size
    ) {
        return cinemaService.getAll(page - 1, size);
    }

    /**
     * GET /api/v1/cinema/{cinemaUid}/films : Просмотр афиши выбранного кинотеатра
     *
     * @param cinemaUid UUID кинотеатра (required)
     * @return Список фильмов в выбранном кинотеатре (status code 200)
     * or Билет не найден (status code 404)
     */
    @Operation(
            operationId = "apiV1CinemaCinemaUidFilmsGet",
            summary = "Просмотр афиши выбранного кинотеатра",
            tags = {"Cinema Service"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список фильмов в выбранном кинотеатре", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = FilmsInCinemaResponse.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Билет не найден", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            }
    )
    @GetMapping(value = "/{cinemaUid}/films")
    FilmsInCinemaResponse getAllFilmsInCinema(
            @Parameter(name = "cinemaUid", description = "UUID кинотеатра", required = true)
            @PathVariable("cinemaUid") UUID cinemaUid
    ) {
        return cinemaService.getAllFilmsInCinema(cinemaUid);
    }

    @PostMapping(value = "/{cinemaUid}/films/{filmUid}/increment-ticket-in-session")
    UUID incrementTicketToSession(
            @Parameter(description = "UUID кинотеатра", required = true)
            @PathVariable("cinemaUid") UUID cinemaUid,
            @Parameter(description = "UUID фильма", required = true)
            @PathVariable("filmUid") UUID filmUid,
            @RequestParam("date") LocalDateTime date
    ) {
        return cinemaService.incrementTicketToSession(cinemaUid, filmUid, date);
    }
}
