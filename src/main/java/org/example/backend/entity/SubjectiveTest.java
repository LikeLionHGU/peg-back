package org.example.backend.entity;

import jakarta.persistence.*;

@Entity
public class SubjectiveTest extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectiveTestId;

    private String  field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    private String field6;

    private String field7;

    private String field8;

    private String field9;

    private String field10;

//    @OneToOne
//    @JoinColumn(name = "mypage_id")
//    private Mypage mypage;
}
