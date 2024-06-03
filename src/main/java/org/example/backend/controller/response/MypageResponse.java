package org.example.backend.controller.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.entity.UserEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MypageResponse extends ApiResponse  {


    private Long mypageId;
    private String nickname;
    private String todayPeg;
    private UserEntity userEntity; // 사용자의 id
    private String profilePictureUrl;


}
