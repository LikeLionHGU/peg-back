package org.example.backend.entity;

import jakarta.persistence.*;

@Entity
public class MultipleChoiceTest extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long multipleChoiceTestId;

    private int field1;

    private int field2;

    private int field3;

    private int field4;

    private int field5;

    private int field6;

    private int field7;

    private int field8;

    private int field9;

    private int field10;

//    @OneToOne
//    @JoinColumn(name = "mypage_id")
//    private Mypage mypage;


}
