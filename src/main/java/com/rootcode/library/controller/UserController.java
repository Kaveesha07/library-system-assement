package com.rootcode.library.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.rootcode.library.dto.NewUserRequestDto;
import com.rootcode.library.dto.UserResponseDto;
import com.rootcode.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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

  @Operation(
          summary = "Create a new user account",
          description = "This endpoint creates a new user account and returns the created user details."
  )
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "201",
                  description = "User account successfully created.",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = UserResponseDto.class)
                  )
          ),
          @ApiResponse(
                  responseCode = "400",
                  description = "Bad Request - Invalid User Details Provided.",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = ErrorResponse.class)
                  )
          ),
          @ApiResponse(
                  responseCode = "409",
                  description = "Conflict - Email already exists or username is taken.",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = ErrorResponse.class)
                  )
          ),
          @ApiResponse(
                  responseCode = "500",
                  description = "Internal Server Error.",
                  content = @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = ErrorResponse.class)
                  )
          )
  })
  @PostMapping("/register")
  public ResponseEntity<UserResponseDto> registerUser(@RequestBody NewUserRequestDto userDetails) {
    return new ResponseEntity<>(userService.createUser(userDetails), CREATED);
  }
}
