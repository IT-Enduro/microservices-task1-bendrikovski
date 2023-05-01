package com.education.filmsservice.repository;

import com.education.filmsservice.model.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {
    List<FilmEntity> getAllByFilmUidIn(List<UUID> ids);
}
