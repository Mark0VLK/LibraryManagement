package com.example.bookservice.service.impl;

import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.model.BookModel;
import com.example.bookservice.repository.AuthorRepository;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.repository.GenreRepository;
import com.example.bookservice.repository.entity.AuthorEntity;
import com.example.bookservice.repository.entity.BookEntity;
import com.example.bookservice.repository.entity.GenreEntity;
import com.example.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bookservice.repository.BookSpecificationProvider.buildSearchCriteria;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookModel> getBooks(String authorName, String authorSurname, String genreName) {
        AuthorEntity author = null;
        GenreEntity genre = null;
        if (authorName != null && authorSurname != null) {
            author = authorRepository.findByNameAndSurname(authorName, authorSurname).orElseThrow(
                    () -> new RuntimeException(format("Автор %s %s не существует в базе", authorName, authorSurname)));
        }
        if (genreName != null) {
            genre = genreRepository.findByName(genreName).orElseThrow(
                    () -> new RuntimeException(format("Жанр %s не существует в базе", genreName)));
        }
        Specification<BookEntity> specification = buildSearchCriteria(author, genre);
        return bookMapper.toModels(bookRepository.findAll(specification));
    }

    @Override
    public BookModel addBook(BookModel model) {
        BookEntity entity = bookMapper.toEntity(model);
        return bookMapper.toModel(bookRepository.save(entity));
    }

    @Override
    public BookModel updateBook(Long bookId, BookModel model) {
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(
                () -> new RuntimeException(format("Книга с ID %d не существует", bookId)));
        AuthorEntity author = authorRepository.findByNameAndSurname(model.authorName(), model.authorSurname())
                .orElseThrow(() -> new RuntimeException(format(
                        "Автор %s %s не существует в базе", model.authorName(), model.authorSurname())));
        GenreEntity genre = genreRepository.findByName(model.genre()).orElseThrow(
                () -> new RuntimeException(format("Жанр %s не существует в базе", model.genre())));
        bookEntity = bookEntity.toBuilder()
                .name(model.name())
                .author(author)
                .genre(genre)
                .build();
        bookEntity = bookRepository.save(bookEntity);
        return bookMapper.toModel(bookEntity);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
