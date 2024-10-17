package com.example.bookservice.repository;

import com.example.bookservice.repository.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findByNameAndSurname(String name, String surname);
}
