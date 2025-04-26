package com.rootcode.library.service;

import com.rootcode.library.dto.NewUserRequestDto;
import com.rootcode.library.dto.UserResponseDto;
import com.rootcode.library.entity.User;

public interface UserService {

    UserResponseDto createUser(NewUserRequestDto userDetails);

    User findUserById(Long userId);
}
