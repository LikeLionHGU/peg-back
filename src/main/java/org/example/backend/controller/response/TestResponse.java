package org.example.backend.controller.response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.dto.TestDto;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestResponse extends ApiResponse  {

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

    public TestResponse(TestDto test) {
        this.field1 = test.getField1();
        this.field2 = test.getField2();
        this.field3 = test.getField3();
        this.field4 = test.getField4();
        this.field5 = test.getField5();
        this.field6 = test.getField6();
        this.field7 = test.getField7();
        this.field8 = test.getField8();
        this.field9 = test.getField9();
        this.field10 = test.getField10();

    }
}
