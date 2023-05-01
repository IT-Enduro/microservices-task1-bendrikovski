package com.education.cinemaservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrorResponse {

  @Schema(name = "message", description = "Информация об ошибке", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  private String message;

}

