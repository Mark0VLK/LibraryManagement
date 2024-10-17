package com.example.bookservice.repository;

import com.example.bookservice.repository.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    Optional<GenreEntity> findByName(String name);
}
