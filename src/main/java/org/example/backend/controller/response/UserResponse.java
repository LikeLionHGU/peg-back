package org.example.backend.controller.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.dto.UserDTO;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserResponse extends ApiResponse{

    private Long Id;
    private String username; // 네이버,구글 식별 이름
    private String name; // 오세훈
    private String nickname; // 오세훈
    private String email;
    private String role;
    private String profilePictureUrl;  // 프로필 사진 링크 필드 추가
    private String todayPeg;

    public UserResponse(UserDTO userDTO) {
        this.Id = userDTO.getId();
        this.username = userDTO.getUsername();
        this.name = userDTO.getName();
        this.nickname = userDTO.getNickname();
        this.email = userDTO.getEmail();
        this.role = userDTO.getRole();
        this.profilePictureUrl = userDTO.getProfilePictureUrl();
        this.todayPeg = userDTO.getTodayPeg();
    }

}
