package com.rootcode.library.dto;

public record BookRequest(
        String title,
        String author,
        int publishedYear,
        int availableCopies
) {}
