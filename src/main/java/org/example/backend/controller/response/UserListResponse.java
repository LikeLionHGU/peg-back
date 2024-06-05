package org.example.backend.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.entity.UserEntity;

import java.util.List;
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class UserListResponse extends ApiResponse {
    private List<UserEntity> users;
    public UserListResponse(List<UserEntity> users){
        this.users = users;
    }

}
