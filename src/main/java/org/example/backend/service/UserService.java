package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.controller.response.UserListResponse;
import org.example.backend.dto.UserDTO;
import org.example.backend.entity.UserEntity;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.repository.UserEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserEntityRepository userEntityRepository;

    public UserDTO getUser(Long userId){ // 여기서 가져오던지 만들던지해야됨

        UserEntity userEntity =
                userEntityRepository
                        .findById(userId)
                        .orElseThrow(UserNotFoundException::new);


        return UserDTO.from(userEntity);
    }




    public void updateUserProfileImage(Long userId, UserDTO userDTO) {
        UserEntity user =
                userEntityRepository
                        .findById(userId)
                        .orElseThrow(UserNotFoundException::new);
        user.setProfilePictureUrl(userDTO.getProfilePictureUrl());

    }

    public List<UserEntity> findAll() {
        List<UserEntity> users = userEntityRepository.findAll();
        return users;
    }

    public List<UserEntity> searchUsersByName(String keyword) {
        return userEntityRepository.findByNameContainingIgnoreCase(keyword);
    }
}
