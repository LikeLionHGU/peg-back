package org.example.backend.controller.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestResultResponse extends ApiResponse {
    int oxTestResult;
    String multipleResult;
    String subjectiveResult;

    public TestResultResponse(int oxTestResult, String multipleResult , String subjectiveResult){
        this.oxTestResult = oxTestResult;
        this.multipleResult = multipleResult;
        this.subjectiveResult = subjectiveResult;
    }

}
