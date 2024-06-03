package org.example.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.dto.TestDto;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OXTest extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OXTestId;

    private String  field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    private String field6;

    private String field7;

    private String field8;

    private String field9;

    private String field10;;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public static OXTest toOXTest (TestDto testDto, UserEntity userEntity) {
        return OXTest.builder()
                .field1(testDto.getField1())
                .field2(testDto.getField2())
                .field3(testDto.getField3())
                .field4(testDto.getField4())
                .field5(testDto.getField5())
                .field6(testDto.getField6())
                .field7(testDto.getField7())
                .field8(testDto.getField8())
                .field9(testDto.getField9())
                .field10(testDto.getField10())
                .userEntity(userEntity)
                .build();
    }

    public void update(TestDto testDto) {
        this.field1 = testDto.getField1();
        this.field2 = testDto.getField2();
        this.field3 = testDto.getField3();
        this.field4 = testDto.getField4();
        this.field5 = testDto.getField5();
        this.field6 = testDto.getField6();
        this.field7 = testDto.getField7();
        this.field8 = testDto.getField8();
        this.field9 = testDto.getField9();
        this.field10 = testDto.getField10();
    }
}
