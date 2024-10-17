package com.example.bookservice.controller.request;

public record BookCreateRequest(
        String name,
        String authorName,
        String authorSurname,
        String genre
) {
}
