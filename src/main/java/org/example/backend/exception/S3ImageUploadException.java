package org.example.backend.exception;

import java.io.IOException;

public class S3ImageUploadException extends IOException{
    private static final String MESSAGE = "S3 이미지 업로드 중 오류가 발생했습니다.";

    public S3ImageUploadException() {
        super(MESSAGE);
    }

}
