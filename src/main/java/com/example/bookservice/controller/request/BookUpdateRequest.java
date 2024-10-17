package com.example.bookservice.controller.request;

public record BookUpdateRequest(
        String name,
        String authorName,
        String authorSurname,
        String genre
) {
}
