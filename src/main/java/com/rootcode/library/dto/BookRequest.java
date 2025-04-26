package com.rootcode.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookRequest {
  private String title;
  private String author;
  private int publishedYear;
  private int availableCopies;
}
