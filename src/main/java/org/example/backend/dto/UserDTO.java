package org.example.backend.dto;

import lombok.*;
import org.example.backend.entity.UserEntity;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long Id;
    private String username; // 네이버,구글 식별 이름
    private String name; // 오세훈
    private String nickname; // 오세훈
    private String email;
    private String role;
    private String profilePictureUrl;  // 프로필 사진 링크 필드 추가
    private String todayPeg;

  public static UserDTO from(UserEntity userEntity) { // response 용도
      return UserDTO.builder()
              .Id(userEntity.getId())
              .username(userEntity.getUsername())
              .name(userEntity.getName())
              .nickname(userEntity.getNikcname())
              .email(userEntity.getEmail())
              .role(userEntity.getRole())
              .profilePictureUrl(userEntity.getProfilePictureUrl())
              .todayPeg(userEntity.getTodayPeg())
              .build();

  }
}
