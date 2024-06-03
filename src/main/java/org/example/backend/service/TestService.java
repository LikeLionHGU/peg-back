package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.TestDto;
import org.example.backend.entity.*;
import org.example.backend.repository.*;
import org.springframework.stereotype.Service;
import org.example.backend.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class TestService {
    private final UserEntityRepository userEntityRepository;
    private final OXTestRepository oxTestRepository;
    private final SubjectiveTestRepository subjectiveTestRepository;
    private final MultipleChoiceTestRepository multipleChoiceTestRepository;

    // oxtest
    public Long addOXTest(TestDto testDto) {

        UserEntity userEntity =
                userEntityRepository.findById(testDto.getUserId()).orElseThrow(UserNotFoundException::new);
            // testDto 에서 userid 가져오기..
        OXTest oxTest = oxTestRepository.save(OXTest.toOXTest(testDto, userEntity)) ; // 찾은 페이지와 , post 객체 연결
        return oxTest.getOXTestId();
    }

    public Long addMultipleTest(TestDto testDto) {

        UserEntity userEntity =
                userEntityRepository.findById(testDto.getUserId()).orElseThrow(UserNotFoundException::new);
        // testDto 에서 userid 가져오기..
        MultipleChoiceTest multipleChoiceTest = multipleChoiceTestRepository.save(MultipleChoiceTest.toMultipleChoiceTest(testDto, userEntity)) ; // 찾은 페이지와 , post 객체 연결
        return multipleChoiceTest.getMultipleChoiceTestId();
    }


    public Long addSubjectiveTest(TestDto testDto) {

        UserEntity userEntity =
                userEntityRepository.findById(testDto.getUserId()).orElseThrow(UserNotFoundException::new);
        // testDto 에서 userid 가져오기..
        SubjectiveTest subjectiveTest = subjectiveTestRepository.save(SubjectiveTest.toSubjectiveTest(testDto, userEntity)) ; // 찾은 페이지와 , post 객체 연결
        return subjectiveTest.getSubjectiveTestId();
    }

    public TestDto getOXTest(Long userId) {

        UserEntity userEntity =
                userEntityRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        OXTest oxTest = oxTestRepository.findOXTestByUserEntity(userEntity);
        return TestDto.from(oxTest);
    }

    public TestDto getMultipleTest(Long userId) {
        UserEntity userEntity =
                userEntityRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        MultipleChoiceTest multipleChoiceTest = multipleChoiceTestRepository.findMultipleChoiceTestByUserEntity(userEntity);
        return TestDto.from(multipleChoiceTest);
    }


    public TestDto getSubjectiveTest(Long userId) {
        UserEntity userEntity =
                userEntityRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        SubjectiveTest subjectiveTest = subjectiveTestRepository.findSubjectiveTestByUserEntity(userEntity);
        return TestDto.from(subjectiveTest);
    }

    public void deleteOXTest(Long userId) {
        UserEntity userEntity =
                userEntityRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        OXTest oxTest = oxTestRepository.findOXTestByUserEntity(userEntity);
        oxTestRepository.deleteById(oxTest.getOXTestId());

    }

    public void deleteMultipleTest(Long userId) {
        UserEntity userEntity =
                userEntityRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        MultipleChoiceTest multipleChoiceTest = multipleChoiceTestRepository.findMultipleChoiceTestByUserEntity(userEntity);
        multipleChoiceTestRepository.deleteById(multipleChoiceTest.getMultipleChoiceTestId());
    }

    public void deleteSubjectiveTest(Long userId) {
        UserEntity userEntity =
                userEntityRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        SubjectiveTest subjectiveTest = subjectiveTestRepository.findSubjectiveTestByUserEntity(userEntity);
        subjectiveTestRepository.deleteById(subjectiveTest.getSubjectiveTestId());
    }


    public void updateOXTest(Long userId, TestDto testDto) {
        UserEntity userEntity =
                userEntityRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        OXTest oxTest = oxTestRepository.findOXTestByUserEntity(userEntity);
        oxTest.update(testDto);
        oxTestRepository.save(oxTest);
    }

    public void updateMultipleTest(Long userId, TestDto testDto) {
        UserEntity userEntity =
                userEntityRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        MultipleChoiceTest multipleChoiceTest = multipleChoiceTestRepository.findMultipleChoiceTestByUserEntity(userEntity);
        multipleChoiceTest.update(testDto);
        multipleChoiceTestRepository.save(multipleChoiceTest);
    }


    public void updateSubjectiveTest(Long userId, TestDto testDto) {
        UserEntity userEntity =
                userEntityRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        SubjectiveTest subjectiveTest = subjectiveTestRepository.findSubjectiveTestByUserEntity(userEntity);
        subjectiveTest.update(testDto);
        subjectiveTestRepository.save(subjectiveTest);
    }
}
