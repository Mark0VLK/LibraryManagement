package com.example.bookservice.service;

import com.example.bookservice.model.BookModel;

import java.util.List;

public interface BookService {
    List<BookModel> getBooks(String authorName,String authorSurname, String genreName);

    BookModel addBook(BookModel request);

    BookModel updateBook(Long bookId, BookModel request);

    void deleteBook(Long bookId);
}
