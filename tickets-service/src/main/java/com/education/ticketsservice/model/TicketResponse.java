package com.education.ticketsservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TicketResponse {
    @Valid
    private UUID ticketUid;

    @Valid
    @Schema(description = "Дата сеанса", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime date;

    @Valid
    @Schema(description = "Ряд", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer row;

    @Valid
    @Schema(description = "Место в зале", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer seat;

    @Valid
    @Schema(description = "Статус")
    private TicketEntity.TicketStatus status;
}

