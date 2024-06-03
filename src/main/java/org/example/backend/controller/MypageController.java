package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.controller.response.ApiResponse;
import org.example.backend.controller.response.UserResponse;
import org.example.backend.dto.CustomOAuth2User;
import org.example.backend.dto.UserDTO;
import org.example.backend.entity.UserEntity;
import org.example.backend.repository.UserEntityRepository;
import org.example.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.example.backend.exception.UserNotFoundException;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/mypage")
public class MypageController {

    private final UserEntityRepository userEntityRepository;
    private final UserService userService;
  @GetMapping
  public ResponseEntity<ApiResponse> mypageRegister(@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
    Long id = customOAuth2User.getId();
    UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(UserNotFoundException::new);
    UserDTO userDTO = userService.getUser(userEntity.getId());
    ApiResponse response = new UserResponse(userDTO);
    return ResponseEntity.ok(response);
  }


}
