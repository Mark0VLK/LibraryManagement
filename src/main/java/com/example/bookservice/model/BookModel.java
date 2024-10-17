package com.example.bookservice.model;

public record BookModel(
        String name,
        String authorName,
        String authorSurname,
        String genre
) {
}
