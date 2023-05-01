package com.education.cinemaservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CinemaResponse {
    @Schema(description = "UID кинотеатра")
    private UUID cinemaUid;

    @Schema(description = "Название кинотеатра")
    private String name;

    @Schema(description = "Адрес кинотеатра")
    private String address;
}

