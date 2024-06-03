package org.example.backend.exception;

public class MypageNotFoundException extends RuntimeException{

    private static final String USER_NOT_FOUND = "일치하는 회원 정보가 없습니다.";

    public MypageNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
