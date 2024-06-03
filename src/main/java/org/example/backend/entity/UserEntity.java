package org.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String username;

    private String name;

    private String nikcname;

    private String email;

    private String role;

    private String profilePictureUrl;  // 프로필 사진 링크 필드 추가

    private String todayPeg;



}
