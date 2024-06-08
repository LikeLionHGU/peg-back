package org.example.backend.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backend.dto.CustomOAuth2User;
import org.example.backend.jwt.JWTUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    public CustomSuccessHandler(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        Long id = customUserDetails.getId(); // ID 가져오기
        String username = customUserDetails.getUsername();

        // Authentication 객체에서 CustomOAuth2User 객체를 가져와 사용자 이름을 얻습니다.

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // Authentication 객체에서 사용자의 역할(권한)을 가져옵니다.

        String token = jwtUtil.createJwt(id,username, role, 1000*60*60*6L);

        // JWTUtil을 사용하여 JWT 토큰을 생성합니다. 토큰에는 사용자 이름, 역할 및 1시간의 유효 기간이 포함됩니다.

        response.addCookie(createCookie("Authorization", token));
        response.sendRedirect("http://localhost:3000/mypage");

        // Authorization 쿠키에 JWT 토큰을 저장하고, 클라이언트를 http://localhost:3000/으로 리디렉션합니다.

    }

    private Cookie createCookie(String key, String value){

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    // createCookie 메서드는 주어진 키와 값으로 쿠키를 생성하고, 유효 시간을 1시간으로 설정하며,
    // 경로를 루트 /로 설정하고, HTTP 전용으로 설정하여 반환합니다.
}
