package com.example.bookservice.repository;

import com.example.bookservice.repository.entity.AuthorEntity;
import com.example.bookservice.repository.entity.BookEntity;
import com.example.bookservice.repository.entity.GenreEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookSpecificationProvider {

    public static Specification<BookEntity> buildSearchCriteria(AuthorEntity author, GenreEntity genre) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (author != null) {
                predicates.add(criteriaBuilder.equal(root.get(BookEntity.Fields.author), author));
            }

            if (genre != null) {
                predicates.add(criteriaBuilder.equal(root.get(BookEntity.Fields.genre), genre));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
