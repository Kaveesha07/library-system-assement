package com.rootcode.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
  private Long id;
  private String name;
  private String email;
}
