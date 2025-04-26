package com.rootcode.library.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BorrowHistoryResponse {
  private Long id;
  private BookResponse borrowedBook;
}
