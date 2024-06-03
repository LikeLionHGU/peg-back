package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.controller.form.OXTestForm;
import org.example.backend.controller.form.TestForm;
import org.example.backend.entity.MultipleChoiceTest;
import org.example.backend.entity.OXTest;
import org.example.backend.entity.SubjectiveTest;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {

    private Long userId;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String field10;

    public static TestDto from(TestForm Test) { // testform에서 userid 가져오기
        return TestDto.builder()
                .userId(Test.getUserId())
                .field1(Test.getField1())
                .field2(Test.getField2())
                .field3(Test.getField3())
                .field4(Test.getField4())
                .field5(Test.getField5())
                .field6(Test.getField6())
                .field7(Test.getField7())
                .field8(Test.getField8())
                .field9(Test.getField9())
                .field10(Test.getField10())
                .build();
    }

  public static TestDto from(OXTest Test) { // testform에서 userid 가져오기
    return TestDto.builder()
        .field1(Test.getField1())
        .field2(Test.getField2())
        .field3(Test.getField3())
        .field4(Test.getField4())
        .field5(Test.getField5())
        .field6(Test.getField6())
        .field7(Test.getField7())
        .field8(Test.getField8())
        .field9(Test.getField9())
        .field10(Test.getField10())
        .build();
        }

    public static TestDto from(MultipleChoiceTest Test) { // testform에서 userid 가져오기
        return TestDto.builder()
                .field1(Test.getField1())
                .field2(Test.getField2())
                .field3(Test.getField3())
                .field4(Test.getField4())
                .field5(Test.getField5())
                .field6(Test.getField6())
                .field7(Test.getField7())
                .field8(Test.getField8())
                .field9(Test.getField9())
                .field10(Test.getField10())
                .build();
    }

    public static TestDto from(SubjectiveTest Test) { // testform에서 userid 가져오기
        return TestDto.builder()
                .field1(Test.getField1())
                .field2(Test.getField2())
                .field3(Test.getField3())
                .field4(Test.getField4())
                .field5(Test.getField5())
                .field6(Test.getField6())
                .field7(Test.getField7())
                .field8(Test.getField8())
                .field9(Test.getField9())
                .field10(Test.getField10())
                .build();
    }

    }
