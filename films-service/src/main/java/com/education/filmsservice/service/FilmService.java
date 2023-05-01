package com.education.filmsservice.service;

import com.education.filmsservice.model.FilmEntity;
import com.education.filmsservice.model.FilmResponse;
import com.education.filmsservice.model.FilmsWithPaginationResponse;
import com.education.filmsservice.repository.FilmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record FilmService(FilmRepository filmRepository) {

    public FilmsWithPaginationResponse getFilms(int page, int size) {
        Page<FilmEntity> filmsPage = filmRepository.findAll(PageRequest.of(page, size));
        return new FilmsWithPaginationResponse(page, size, filmsPage.getTotalElements(), mapFilms(filmsPage.getContent()));
    }

    public List<FilmResponse> getAll(List<UUID> ids) {
        return mapFilms(filmRepository.getAllByFilmUidIn(ids));
    }

    private List<FilmResponse> mapFilms(List<FilmEntity> content) {
        return content.stream()
                .map(entity ->
                        new FilmResponse(
                                entity.getFilmUid(),
                                entity.getName(),
                                entity.getRating(),
                                entity.getDirector(),
                                entity.getProducer(),
                                entity.getGenre()))
                .toList();
    }
}
