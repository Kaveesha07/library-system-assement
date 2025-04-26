package com.rootcode.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewUserRequestDto {
  private Long id;
  private String name;
  private String email;
  private String password;
}
