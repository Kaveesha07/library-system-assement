package com.rootcode.library.dto;

import java.time.LocalDateTime;

public record BorrowHistoryResponse(
        String bookTitle,
        String author,
        LocalDateTime borrowedAt,
        LocalDateTime returnedAt
) {}
