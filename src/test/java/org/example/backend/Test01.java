package org.example.backend;

import org.example.backend.jwt.JWTUtil;

import java.util.Base64;

public class Test01 {
    public static void main(String[] args) {
        JWTUtil jwtUtil = new JWTUtil("yourSecretKeyHere"); // 실제 secretKey를 넣으세요
        Long userId = 12345L;
        String username = "user";
        String role = "ROLE_USER";
        Long expirationTime = 1000 * 60 * 10L; // 10분 유효

        String token = jwtUtil.createJwt(userId, username, role, expirationTime);
        System.out.println("Generated JWT Token: " + token);

        // 디코딩하여 클레임 확인
        String[] parts = token.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
        System.out.println("Decoded Payload: " + payload);
    }
}
