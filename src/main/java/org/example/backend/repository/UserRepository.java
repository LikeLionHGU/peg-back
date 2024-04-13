package org.example.backend.repository;

import org.example.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long > {

    UserEntity findByUsername(String username);
}
