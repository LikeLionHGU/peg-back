package org.example.backend.controller;

import lombok.RequiredArgsConstructor;

import org.example.backend.controller.form.TestForm;
import org.example.backend.controller.response.ApiResponse;
import org.example.backend.controller.response.TestIdResponse;
import org.example.backend.controller.response.TestResponse;
import org.example.backend.service.TestService;
import org.example.backend.dto.TestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    @PostMapping("/OXtest")
    public ResponseEntity<ApiResponse> addOXTest(@RequestBody TestForm testForm){
        System.out.println("호출되었음.");
        Long oxTestId = testService.addOXTest(TestDto.from(testForm)); //testform을 받아서 from 메서드를 사용해서 dto 반환
        ApiResponse response = new TestIdResponse(oxTestId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/multiplechoicetest")
    public ResponseEntity<ApiResponse> addMutilpleTest(@RequestBody TestForm testForm){
        System.out.println("호출되었음.");
        Long multipleTestId = testService.addMultipleTest(TestDto.from(testForm)); //testform을 받아서 from 메서드를 사용해서 dto 반환
        ApiResponse response = new TestIdResponse(multipleTestId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/subjectivetest")
    public ResponseEntity<ApiResponse> addSubjectiveTest(@RequestBody TestForm testForm){
        System.out.println("호출되었음.");
        Long subjectiveTestId = testService.addSubjectiveTest(TestDto.from(testForm)); //testform을 받아서 from 메서드를 사용해서 dto 반환
        ApiResponse response = new TestIdResponse(subjectiveTestId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/OXtest/{userId}")
    public ResponseEntity<ApiResponse> getOXTest(@PathVariable Long userId ){
        System.out.println("호출되었음.");
        TestDto oxTestDto = testService.getOXTest(userId);
        ApiResponse response = new TestResponse(oxTestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/multiplechoicetest/{userId}")
    public ResponseEntity<ApiResponse> getMultipleTest(@PathVariable Long userId ){
        System.out.println("호출되었음.");
        TestDto multipleTestDto = testService.getMultipleTest(userId);
        ApiResponse response = new TestResponse(multipleTestDto);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/subjectivetest/{userId}")
    public ResponseEntity<ApiResponse> getSubjectiveTest(@PathVariable Long userId ){
        System.out.println("호출되었음.");
        TestDto subjectiveTestDto = testService.getSubjectiveTest(userId);
        ApiResponse response = new TestResponse(subjectiveTestDto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/OXtest/{userId}")
    public ResponseEntity<ApiResponse> updateOXTest(@PathVariable Long userId, @RequestBody TestForm TestForm){
        testService.updateOXTest(userId, TestDto.from(TestForm));
        ApiResponse response = new TestIdResponse(userId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/multiplechoicetest/{userId}")
    public ResponseEntity<ApiResponse> updateMultipleTest(@PathVariable Long userId, @RequestBody TestForm TestForm){
        testService.updateMultipleTest(userId, TestDto.from(TestForm));
        ApiResponse response = new TestIdResponse(userId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/subjectivetest/{userId}")
    public ResponseEntity<ApiResponse> updateSubjectiveTest(@PathVariable Long userId, @RequestBody TestForm TestForm){
        testService.updateSubjectiveTest(userId, TestDto.from(TestForm));
        ApiResponse response = new TestIdResponse(userId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("OXtest/{userId}")
    public ResponseEntity<ApiResponse> deleteOXTest(@PathVariable Long userId) {
        System.out.println("호출되었음.");
        testService.deleteOXTest(userId);
        ApiResponse response = new TestIdResponse(userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("multiplechoicetest/{userId}")
    public ResponseEntity<ApiResponse> deleteMultipleTest(@PathVariable Long userId) {
        System.out.println("호출되었음.");
        testService.deleteMultipleTest(userId);
        ApiResponse response = new TestIdResponse(userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("subjectivetest/{userId}")
    public ResponseEntity<ApiResponse> subjectiveTest(@PathVariable Long userId) {
        System.out.println("호출되었음.");
        testService.deleteSubjectiveTest(userId);
        ApiResponse response = new TestIdResponse(userId);
        return ResponseEntity.ok(response);
    }




}
