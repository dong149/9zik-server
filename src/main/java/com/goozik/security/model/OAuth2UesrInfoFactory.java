package com.goozik.security.model;

import com.goozik.model.constants.ProviderType;
import java.util.Map;

public class OAuth2UesrInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(
        ProviderType providerType,
        Map<String, Object> attributes) {

        switch (providerType) {
            case GOOGLE:
                return new GoogleOAuth2UserInfo(attributes);
            case GITHUB:
                return new GithubOAuth2UserInfo(attributes);
            default:
                throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
