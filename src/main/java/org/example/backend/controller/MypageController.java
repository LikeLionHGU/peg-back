package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.controller.response.ApiResponse;
import org.example.backend.controller.response.TestResultResponse;
import org.example.backend.controller.response.UserListResponse;
import org.example.backend.controller.response.UserResponse;
import org.example.backend.dto.CustomOAuth2User;
import org.example.backend.dto.UserDTO;
import org.example.backend.entity.UserEntity;
import org.example.backend.repository.UserEntityRepository;
import org.example.backend.service.S3Uploader;
import org.example.backend.service.TestService;
import org.example.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.example.backend.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/mypage")
public class MypageController {

    private final UserEntityRepository userEntityRepository;
    private final UserService userService;
    private final TestService testService;
    private final S3Uploader s3Uploader;

  @GetMapping
  public ResponseEntity<ApiResponse> mypageRegister(@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
    Long id = customOAuth2User.getId();
    UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(UserNotFoundException::new);
    UserDTO userDTO = userService.getUser(userEntity.getId());
    ApiResponse response = new UserResponse(userDTO);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/users")
  public ResponseEntity<ApiResponse> getAllMember(){
    List<UserEntity> users = userService.findAll();
    ApiResponse response = new UserListResponse(users);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/users/search")
  public ResponseEntity<ApiResponse> searchUsers(@RequestParam String keyword) {
    List<UserEntity> users = userService.searchUsersByName(keyword);
    ApiResponse response = new UserListResponse(users);
    return ResponseEntity.ok(response);
  }


  @GetMapping("/result/{userId}")
  public ResponseEntity<ApiResponse> getAllResult(@PathVariable Long userId){
    int oxTestResult = testService.getOXTestResult(userId);
    String multipleTestResult = testService.getMultipleTestResult(userId);
    String subjectiveResult = testService.getSubjectiveResult(userId);
    ApiResponse response = new TestResultResponse(oxTestResult, multipleTestResult,subjectiveResult);
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/profile-image")
  public void uploadPicture (
          @RequestParam Long userId , @RequestParam("image") MultipartFile image)  throws IOException {

    String imageURL = s3Uploader.upload(image, "peg/");
    System.out.println(imageURL);
    UserDTO userDTO = UserDTO.builder().profilePictureUrl(imageURL).build();
    userDTO.setProfilePictureUrl(imageURL);
    userService.updateUserProfileImage(userId, userDTO);

  }


}
