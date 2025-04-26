package com.rootcode.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BorrowRequest {
  private Long userId;
  private Long bookId;
}
