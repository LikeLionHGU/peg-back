package org.example.backend.repository;


import org.example.backend.entity.MultipleChoiceTest;
import org.example.backend.entity.SubjectiveTest;
import org.example.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectiveTestRepository extends JpaRepository<SubjectiveTest, Long > {


    SubjectiveTest findSubjectiveTestByUserEntity(UserEntity userEntity);
}
