package com.education.cinemaservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class CinemaWithPaginationResponse {
    @Schema(description = "Номер страницы")
    private int page;

    @Schema(description = "Количество элементов на странице")
    private int pageSize;

    @Schema(description = "Общее количество элементов")
    private long totalElements;

    private List<CinemaResponse> items;
}

