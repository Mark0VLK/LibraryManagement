package com.example.bookservice.repository;

import com.example.bookservice.repository.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {
}
