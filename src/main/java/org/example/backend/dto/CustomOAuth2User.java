package org.example.backend.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final UserDTO userDTO;

    public CustomOAuth2User(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }
    // null을 반환하므로 이 구현체에서는 OAuth2 사용자의 속성을 제공하지 않습니다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return userDTO.getRole();
            }
        });

        return collection;

    }
    //  GrantedAuthority 객체의 컬렉션을 반환합니다. 여기서는 익명 클래스 구현을 사용하여 새로운 GrantedAuthority 객체를 생성하고,
    //  권한은 UserDTO 객체의 role 속성으로 설정됩니다.

    @Override
    public String getName() {
        return userDTO.getName();
    }

    public String getUsername(){
        return userDTO.getUsername();
    }

}
