package org.example.backend.entity;


import jakarta.persistence.*;

@Entity
public class OXTest extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OXTestId;

    private boolean field1;

    private boolean field2;

    private boolean field3;

    private boolean field4;

    private boolean field5;

    private boolean field6;

    private boolean field7;

    private boolean field8;

    private boolean field9;

    private boolean field10;

//    @OneToOne
//    @JoinColumn(name = "mypage_id")
//    private Mypage mypage;

}
