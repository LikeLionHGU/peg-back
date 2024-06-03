package org.example.backend.repository;

import org.example.backend.entity.OXTest;
import org.example.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OXTestRepository extends JpaRepository<OXTest, Long > {
    OXTest findOXTestByUserEntity(UserEntity user);

    void deleteOXTestByUserEntity(UserEntity userEntity);
}
