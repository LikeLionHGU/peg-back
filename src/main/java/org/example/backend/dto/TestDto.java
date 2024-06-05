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
import java.lang.reflect.Method;

import java.lang.reflect.Method;

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



    public static int fromToResult(OXTest oxTest) {
        int result = 0;
        try {
            for (int i = 1; i <= 10; i++) {
                Method method = oxTest.getClass().getMethod("getField" + i);
                String value = (String) method.invoke(oxTest);
                if ("O".equals(value)) {
                    result += 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String fromToResult(MultipleChoiceTest multipleChoiceTest) {
        int[] results = new int[4];

        String[] fields = {
                multipleChoiceTest.getField1(),
                multipleChoiceTest.getField2(),
                multipleChoiceTest.getField3(),
                multipleChoiceTest.getField4(),
                multipleChoiceTest.getField5()
        };

        for (String field : fields) {
            int value = Integer.parseInt(field) - 1;
            if (value >= 0 && value < 4) {
                results[value] += 1;
            }
        }

        // 가장 큰 결과값을 가진 인덱스 찾기
        int maxIndex = 0;
        for (int i = 1; i < results.length; i++) {
            if (results[i] > results[maxIndex]) {
                maxIndex = i;
            }
        }

        // 인덱스에 따라 문자열 반환
        switch (maxIndex) {
            case 0:
                return "result1";
            case 1:
                return "result2";
            case 2:
                return "result3";
            case 3:
                return "result4";
            default:
                throw new IllegalStateException("Unexpected value: " + maxIndex);
        }
    }



}
