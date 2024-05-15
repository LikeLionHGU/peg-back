package org.example.backend.entity;

import jakarta.persistence.*;

@Entity
public class Mypage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mypageId;

    private String  todayPeg;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;


}
