package com.education.ticketsservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * TicketPurchaseRequest
 */

@Data
public class TicketPurchaseRequest {
  @Valid
  @Schema(description = "Дата сеанса", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  private LocalDateTime date;

  @Valid
  @Schema(description = "Ряд", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  private int row;

  @Valid
  @Schema(description = "Место в зале", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  private int seat;
}

