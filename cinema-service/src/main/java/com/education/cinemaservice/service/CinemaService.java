package com.education.cinemaservice.service;

import com.education.cinemaservice.client.FilmClient;
import com.education.cinemaservice.model.CinemaEntity;
import com.education.cinemaservice.model.CinemaResponse;
import com.education.cinemaservice.model.CinemaWithPaginationResponse;
import com.education.cinemaservice.model.FilmResponse;
import com.education.cinemaservice.model.FilmSessionEntity;
import com.education.cinemaservice.model.FilmsInCinemaResponse;
import com.education.cinemaservice.repository.CinemaRepository;
import com.education.cinemaservice.repository.FilmSessionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public record CinemaService(CinemaRepository cinemaRepository,
                            FilmSessionRepository filmSessionRepository,
                            FilmClient filmClient) {

    public CinemaWithPaginationResponse getAll(int page, int size) {
        Page<CinemaEntity> cinemasPage = cinemaRepository.findAll(PageRequest.of(page, size));
        return CinemaWithPaginationResponse.builder()
                .page(page)
                .pageSize(size)
                .totalElements(cinemasPage.getTotalElements())
                .items(mapToResponse(cinemasPage.getContent()))
                .build();
    }

    public FilmsInCinemaResponse getAllFilmsInCinema(UUID cinemaUid) {
        List<UUID> filmsInCinema =
                filmSessionRepository.findAllByCinemaUid(cinemaUid).stream()
                        .map(FilmSessionEntity::getFilmUid)
                        .toList();

        CinemaEntity cinema = cinemaRepository.findByCinemaUid(cinemaUid);
        List<FilmResponse> films = filmClient.getFilms(filmsInCinema);

        return FilmsInCinemaResponse.builder()
                .cinemaUid(cinema.getCinemaUid())
                .name(cinema.getName())
                .address(cinema.getAddress())
                .films(films)
                .build();
    }


    private List<CinemaResponse> mapToResponse(List<CinemaEntity> content) {
        return content.stream().map(
                entity -> CinemaResponse.builder()
                        .cinemaUid(entity.getCinemaUid())
                        .name(entity.getName())
                        .address(entity.getAddress())
                        .build()
        ).toList();
    }

    public UUID incrementTicketToSession(UUID cinemaUid, UUID filmUid, LocalDateTime date) {
        FilmSessionEntity session = filmSessionRepository.findByCinemaUidAndFilmUidAndDate(cinemaUid, filmUid, date);
        if (session != null && session.getTotalSeats() > session.getBookedSeats()) {
            FilmSessionEntity updated = session.toBuilder().bookedSeats(session.getBookedSeats() + 1).build();
            filmSessionRepository.save(updated);
            return session.getSessionUid();
        }
        return null;
    }
}
