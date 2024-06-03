package org.example.backend.repository;

import org.example.backend.entity.MultipleChoiceTest;
import org.example.backend.entity.OXTest;
import org.example.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultipleChoiceTestRepository extends JpaRepository<MultipleChoiceTest, Long > {

    MultipleChoiceTest findMultipleChoiceTestByUserEntity(UserEntity user);

}
