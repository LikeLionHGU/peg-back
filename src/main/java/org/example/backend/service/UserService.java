package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.UserDTO;
import org.example.backend.entity.UserEntity;
import org.example.backend.exception.UserNotFoundException;
import org.example.backend.repository.UserEntityRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityRepository userEntityRepository;

    public UserDTO getUser(Long userId){ // 여기서 가져오던지 만들던지해야됨

        UserEntity userEntity =
                userEntityRepository
                        .findById(userId)
                        .orElseThrow(UserNotFoundException::new);


        return UserDTO.from(userEntity);
    }

}
