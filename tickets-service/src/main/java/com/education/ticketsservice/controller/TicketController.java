package com.education.ticketsservice.controller;

import com.education.ticketsservice.model.ErrorResponse;
import com.education.ticketsservice.model.TicketEntity;
import com.education.ticketsservice.model.TicketPurchaseRequest;
import com.education.ticketsservice.model.TicketResponse;
import com.education.ticketsservice.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
public record TicketController(TicketService ticketService) {

    /**
     * POST /api/v1/tickets/cinema/{cinemaUid}/films/{filmUid} : Покупка билета
     *
     * @param xUserName             Имя пользователя (required)
     * @param cinemaUid             UUID кинотеатра (required)
     * @param filmUid               UUID фильма (required)
     * @param ticketPurchaseRequest (optional)
     * @return Билет успешно куплен (status code 201)
     */
    @Operation(
            operationId = "apiV1TicketsCinemaCinemaUidFilmsFilmUidPost",
            summary = "Покупка билета",
            tags = {"Ticket Service"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Билет успешно куплен")
            }
    )
    @PostMapping("/cinema/{cinemaUid}/films/{filmUid}")
    ResponseEntity<Object> buyTicket(
            @NotNull @Parameter(name = "X-User-Name", description = "Имя пользователя", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "X-User-Name", required = true) String xUserName,
            @Parameter(name = "cinemaUid", description = "UUID кинотеатра", required = true, in = ParameterIn.PATH) @PathVariable("cinemaUid") UUID cinemaUid,
            @Parameter(name = "filmUid", description = "UUID фильма", required = true, in = ParameterIn.PATH) @PathVariable("filmUid") UUID filmUid,
            @Parameter(name = "TicketPurchaseRequest", description = "") @Valid @RequestBody(required = false) TicketPurchaseRequest ticketPurchaseRequest,
            UriComponentsBuilder builder
    ) {
        TicketEntity ticket = ticketService.buyTicket(xUserName, cinemaUid, filmUid, ticketPurchaseRequest);
        UriComponents uriComponents = builder.path("/{ticketUid}").buildAndExpand(ticket.getTicketUid().toString());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }


    /**
     * DELETE /api/v1/tickets/{ticketUid} : Возврат билета
     *
     * @param ticketUid UUID билета (required)
     * @param xUserName Имя пользователя (required)
     * @return Возврат билета успешно выполнен (status code 204)
     * or Билет не найден (status code 404)
     * or До сеанса осталось меньше одного часа (status code 409)
     */
    @Operation(
            operationId = "apiV1TicketsTicketUidDelete",
            summary = "Возврат билета",
            tags = {"Ticket Service"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Возврат билета успешно выполнен"),
                    @ApiResponse(responseCode = "404", description = "Билет не найден", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "409", description = "До сеанса осталось меньше одного часа", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            }
    )
    @DeleteMapping("/{ticketUid}")
    public ResponseEntity<Object> deleteTicket(
            @Parameter(name = "ticketUid", description = "UUID билета", required = true, in = ParameterIn.PATH) @PathVariable("ticketUid") UUID ticketUid,
            @NotNull @Parameter(name = "X-User-Name", description = "Имя пользователя", required = true, in = ParameterIn.HEADER) @RequestHeader(value = "X-User-Name", required = true) String xUserName
    ) {
        ticketService.deleteTicket(ticketUid, xUserName);
        return ResponseEntity.noContent().build();
    }


    /**
     * GET /api/v1/tickets/{ticketUid} : Информация по конкретному билету
     *
     * @param ticketUid UUID билета (required)
     * @param xUserName Имя пользователя (required)
     * @return Информация по конкретному билету (status code 200)
     * or Билет не найден (status code 404)
     */
    @Operation(
            operationId = "apiV1TicketsTicketUidGet",
            summary = "Информация по конкретному билету",
            tags = {"Ticket Service"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация по конкретному билету", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TicketResponse.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Билет не найден", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            }
    )
    @GetMapping("/{ticketUid}")
    public TicketResponse getTicket(
            @Parameter(name = "ticketUid", description = "UUID билета", required = true, in = ParameterIn.PATH)
            @PathVariable("ticketUid") UUID ticketUid,
            @NotNull @Parameter(name = "X-User-Name", description = "Имя пользователя", required = true, in = ParameterIn.HEADER)
            @RequestHeader(value = "X-User-Name", required = true) String xUserName
    ) {
        return ticketService.getTicket(ticketUid, xUserName);
    }
}
