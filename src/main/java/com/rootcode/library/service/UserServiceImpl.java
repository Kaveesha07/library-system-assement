package com.rootcode.library.service;

import com.rootcode.library.dto.NewUserRequestDto;
import com.rootcode.library.dto.UserResponseDto;
import com.rootcode.library.entity.User;
import com.rootcode.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;


  @Override
  public UserResponseDto createUser(NewUserRequestDto userDetails) {
    User newUser = modelMapper.map(userDetails, User.class);

//    String encodedPassword = passwordEncoder.encode(userDetails.getPassword());
//    newUser.setPassword(encodedPassword);

    User savedUser = userRepository.save(newUser);

    return modelMapper.map(savedUser, UserResponseDto.class);
  }

  @Override
  public User findUserById(Long userId) {
    return userRepository
        .findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }



//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//    User user =
//        userRepository
//            .findByEmail(username)
//            .orElseThrow(
//                () -> new UsernameNotFoundException("User not found with email: " + username));
//
//    return org.springframework.security.core.userdetails.User.builder()
//        .username(user.getEmail())
//        .password(user.getPassword())
//        .build();
//  }
}
