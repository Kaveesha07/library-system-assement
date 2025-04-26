package com.rootcode.library.dto;

public record NewUserRequestDto (
        Long id,
        String name,
        String email,
        String password) {

}
