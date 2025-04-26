package com.rootcode.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BorrowHistoryResponse {
  private String bookTitle;
  private String author;
  private LocalDateTime borrowedAt;
  private LocalDateTime returnedAt;
}

