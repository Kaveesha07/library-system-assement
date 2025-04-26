package com.rootcode.library.dto;

public record BookResponse(
        Long id,
        String title,
        String author,
        int publishedYear,
        int availableCopies
) {}
