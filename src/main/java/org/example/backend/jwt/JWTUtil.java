package org.example.backend.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey; // JWT 토큰을 생성하고 검증하는 데 사용되는 비밀 키를 저장할 필드입니다.

    public JWTUtil(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        // 생성자에서 @Value 애노테이션을 사용하여 application.properties 파일에서 spring.jwt.secret 값을 가져옵니다.
        // 이 값을 사용하여 SecretKeySpec 인스턴스를 생성하고 secretKey 필드에 할당합니다.
    }

    public Long getId(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("Id", Long.class); // "Id"로 수정
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("username", String.class);
        // JWT 토큰에서 사용자 이름을 추출하는 메서드입니다.
        // Jwts.parser()를 사용하여 토큰을 파싱하고,
        // verifyWith(secretKey)를 사용하여 토큰을 검증한 다음,
        // getPayload().get("username", String.class)를 사용하여 "username" 클레임 값을 가져옵니다.
    }

    public String getRole(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
        // JWT 토큰에서 역할(권한)을 추출하는 메서드입니다. 과정은 getUsername 메서드와 유사하지만,
        // "role" 클레임 값을 가져옵니다.
    }

    public Boolean isExpired(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
        // JWT 토큰이 만료되었는지 확인하는 메서드입니다. 토큰에서 "expiration" 클레임 값을 가져와 현재 시간과 비교합니다.
        // 만료 시간이 현재 시간보다 이전이면 true를 반환합니다.
    }

    public String createJwt(Long id, String username, String role, Long expiredMs) {
        String token = Jwts.builder()
                .claim("Id", id) // "Id"로 수정
                .claim("username", username)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();


        return token;
    }

    // JWT 토큰을 생성하는 메서드입니다. Jwts.builder()를 사용하여 토큰을 작성하고,
        // "Id", "username"과 "role" 클레임을 추가합니다. issuedAt은 토큰 발급 시간을 설정하고,
        // expiration은 만료 시간을 설정합니다. 마지막으로 signWith(secretKey)를 사용하여 토큰에 서명한 다음,
        // compact()를 호출하여 문자열로 반환합니다.

}
