package com.education.ticketsservice.service;

import com.education.ticketsservice.client.CinemaClient;
import com.education.ticketsservice.model.TicketEntity;
import com.education.ticketsservice.model.TicketPurchaseRequest;
import com.education.ticketsservice.model.TicketResponse;
import com.education.ticketsservice.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record TicketService(TicketRepository ticketRepository, CinemaClient cinemaClient) {

    public TicketEntity buyTicket(String xUserName, UUID cinemaUid, UUID filmUid, TicketPurchaseRequest request) {
        UUID sessionUid = cinemaClient.incrementTicketInSession(cinemaUid, filmUid, request.getDate());

        if (sessionUid == null) {
            throw new RuntimeException("No tickets");
        }

        TicketEntity ticket = TicketEntity.builder()
                .filmUid(filmUid)
                .name(xUserName)
                .date(request.getDate())
                .sessionUid(sessionUid)
                .row(request.getRow())
                .seat(request.getSeat())
                .status(TicketEntity.TicketStatus.BOOKED)
                .build();

        return ticketRepository.save(ticket);
    }

    public TicketResponse getTicket(UUID ticketUid, String xUserName) {
        TicketEntity ticket = ticketRepository.findByTicketUidAndName(ticketUid, xUserName);
        return TicketResponse.builder()
                .ticketUid(ticket.getTicketUid())
                .status(ticket.getStatus())
                .date(ticket.getDate())
                .row(ticket.getRow())
                .seat(ticket.getSeat())
                .build();
    }

    public void deleteTicket(UUID ticketUid, String xUserName) {
        TicketEntity ticket = ticketRepository.findByTicketUidAndName(ticketUid, xUserName);
        ticketRepository.save(ticket.toBuilder().status(TicketEntity.TicketStatus.CANCELED).build());
    }
}
