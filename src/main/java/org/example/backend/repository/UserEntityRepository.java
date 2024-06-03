package org.example.backend.repository;

import org.example.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long > {

    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);

}