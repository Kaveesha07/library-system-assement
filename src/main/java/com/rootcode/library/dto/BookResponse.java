package com.rootcode.library.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookResponse {
  private Long id;
  private String title;
  private String author;
  private int publishedYear;
  private int availableCopies;
}
