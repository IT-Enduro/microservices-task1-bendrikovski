package com.education.cinemaservice.client;

import com.education.cinemaservice.model.FilmResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FilmClient {
    private final WebClient webClient;

    public FilmClient(WebClient.Builder webClientBuilder, @Value("${microservices.film-microservice-url}") String filmServiceUrl) {
        this.webClient = webClientBuilder.baseUrl("http://" + filmServiceUrl).build();
    }

    public List<FilmResponse> getFilms(List<UUID> ids) {
        FilmResponse[] response = this.webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/films").build())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(ids.toArray())
                .retrieve()
                .bodyToMono(FilmResponse[].class)
//                .retryWhen(Retry.backoff(3, Duration.ofSeconds(5)))
                .block();

        return Arrays.stream(Objects.requireNonNull(response)).toList();
    }
}
