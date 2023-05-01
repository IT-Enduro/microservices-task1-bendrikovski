package com.education.ticketsservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class CinemaClient {
    private final WebClient webClient;

    public CinemaClient(WebClient.Builder webClientBuilder, @Value("${microservices.cinema-microservice-url}") String cinemaServiceUrl) {
        this.webClient = webClientBuilder.baseUrl("http://" + cinemaServiceUrl).build();
    }

    public UUID incrementTicketInSession(UUID cinemaUid, UUID filmUid, LocalDateTime date) {
        return this.webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/cinema/{cinemaUid}/films/{filmUid}/increment-ticket-in-session")
                        .queryParam("date", date.toString())
                        .build(Map.of("cinemaUid", cinemaUid, "filmUid", filmUid))
                )
                .retrieve()
                .bodyToMono(UUID.class)
                .block();
    }

}
