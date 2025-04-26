package com.rootcode.library.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.rootcode.library.dto.NewUserRequestDto;
import com.rootcode.library.dto.UserResponseDto;
import com.rootcode.library.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponseDto> registerUser(@RequestBody NewUserRequestDto userDetails) {
    return new ResponseEntity<>(userService.createUser(userDetails), CREATED);
  }
}
