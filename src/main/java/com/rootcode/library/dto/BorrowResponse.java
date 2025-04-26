package com.rootcode.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BorrowResponse {
  private Long userId;
  private BookResponse book;
  private LocalDate borrowDate;
  private LocalDate returnDate;
}
