package com.education.cinemaservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FilmsInCinemaResponse {

  @Schema(name = "cinemaUid", description = "UID кинотеатра")
  private UUID cinemaUid;

  @Schema(name = "name", description = "Название кинотеатра")
  private String name;

  @Schema(name = "address", description = "Адрес кинотеатра")
  private String address;

  private List<FilmResponse> films;
}

