package org.example.backend.service;

import org.example.backend.dto.*;
import org.example.backend.entity.UserEntity;
import org.example.backend.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    // DefaultOAuth2UserService를 상속받아 OAuth2 인증 프로세스를 확장합니다.
    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // 이 메서드는 DefaultOAuth2UserService의 loadUser 메서드를 오버라이드합니다.
    // OAuth2 인증 과정에서 사용자 정보를 로드하는 역할을 합니다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());
        // 상위 클래스의 loadUser 메서드를 호출하여 OAuth2User 객체를 가져오고, 콘솔에 출력합니다.

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 네이버인지 구글인지 알아오는 변수

        OAuth2Response oAuth2Response = null;

        if(registrationId.equals("naver")) {
        // 네이버는 네이버대로 인증데이터를 받아야하고 , 구글은 구글대로 인증데이터를 받아야 한다.
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }

        else if (registrationId.equals("google")){

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }

        else {

            return null;
        }

        // 이 부분은 OAuth2 공급자(Naver 또는 Google)에 따라 OAuth2Response 객체를 생성합니다. 지원되지 않는 공급자인 경우 null을 반환합니다.

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String username = oAuth2Response.getProvider()+ " " + oAuth2Response.getProviderId();

        UserEntity existData = userRepository.findByUsername(username);

        if(existData == null) {
            // 새로운 사용자 생성 및 저장 로직
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setName(oAuth2Response.getName());
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(userDTO);

            // 새로운 UserEntity를 생성하고 OAuth2 공급자에서 받은 정보로 초기화합니다. 그리고 데이터베이스에 저장한 후,
            // UserDTO를 생성하고 CustomOAuth2User 객체를 반환합니다.
        }

        else {
            // 기존 사용자 업데이트 로직
            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());

            userRepository.save(existData);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());

            return new CustomOAuth2User(userDTO);

        // 기존 UserEntity의 이메일과 이름을 업데이트하고, 데이터베이스에 저장합니다.
            // 그리고 UserDTO를 생성하고 CustomOAuth2User 객체를 반환합니다.

    }
    }
}
