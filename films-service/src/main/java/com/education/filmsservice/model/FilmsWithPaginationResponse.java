package com.education.filmsservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FilmsWithPaginationResponse {
  @Schema(description = "Номер страницы")
  private int page;

  @Schema(description = "Количество элементов на странице")
  private int pageSize;

  @Schema( description = "Общее количество элементов")
  private long totalElements;

  private List<FilmResponse> items = null;
}

