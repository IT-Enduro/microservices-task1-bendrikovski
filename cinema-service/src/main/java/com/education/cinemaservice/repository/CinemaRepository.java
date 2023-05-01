package com.education.cinemaservice.repository;

import com.education.cinemaservice.model.CinemaEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CinemaRepository extends JpaRepository<CinemaEntity, Integer> {
    @NonNull Page<CinemaEntity> findAll(@NonNull Pageable pageable);

    CinemaEntity findByCinemaUid(UUID cinemaUid);
}
