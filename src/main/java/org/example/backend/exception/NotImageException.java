package org.example.backend.exception;

public class NotImageException extends RuntimeException {
    private static final String MESSAGE = "이미지 파일이 아닙니다.";
    public NotImageException() {
        super(MESSAGE);
    }
}
