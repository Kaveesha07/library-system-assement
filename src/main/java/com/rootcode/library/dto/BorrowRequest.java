package com.rootcode.library.dto;

public record BorrowRequest(
        Long userId,
        Long bookId
) {}
