package org.example.backend.dto;

import java.util.Map;

public class GoogleResponse implements OAuth2Response {
// 데이터를 받을 수 있는 바구니인 dto
    private final Map<String, Object> attribute;

    public GoogleResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return attribute.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }

    public String getProfilePictureUrl() { return (String) attribute.get("picture").toString();  }
}
