package com.education.cinemaservice.repository;

import com.education.cinemaservice.model.FilmSessionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FilmSessionRepository extends CrudRepository<FilmSessionEntity, Integer> {

    List<FilmSessionEntity> findAllByCinemaUid(UUID cinemaUid);

    FilmSessionEntity findByCinemaUidAndFilmUidAndDate(UUID cinemaUid, UUID filmUid, LocalDateTime date);
}
