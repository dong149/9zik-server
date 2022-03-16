package com.goozik.security.service;

import com.goozik.model.constants.ProviderType;
import com.goozik.model.constants.Role;
import com.goozik.model.entity.User;
import com.goozik.repository.UserRepository;
import com.goozik.security.model.OAuth2UesrInfoFactory;
import com.goozik.security.model.OAuth2UserInfo;
import com.goozik.security.model.OAuthAttributes;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final String EMPTY_PASSWORD = "";

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return process(userRequest, user);
        } catch (AuthenticationException ex) {
            throw new OAuth2AuthenticationException(ex.getMessage());
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType = ProviderType.valueOf(
            userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UesrInfoFactory.getOAuth2UserInfo(
            providerType,
            user.getAttributes());

        User savedUser = userRepository.findByEmail(userInfo.getEmail()).orElse(null);

        if (savedUser != null) {
            if (providerType != savedUser.getProviderType()) {
                throw new OAuth2AuthenticationException(
                    "Looks like you're signed up with " + providerType +
                        " account. Please use your " + savedUser.getProviderType()
                        + " account to login."
                );
            }
            updateUser(savedUser, userInfo);
        } else {
            savedUser = createUser(userInfo, providerType);
        }

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                                                  .getProviderDetails()
                                                  .getUserInfoEndpoint()
                                                  .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(
            registrationId,
            userNameAttributeName,
            userInfo.getAttributes());

        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority(savedUser.getRoleKey())),
            attributes.getAttributes(),
            attributes.getNameAttributeKey());
    }

    private User createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
        User user = User.builder()
                        .name(userInfo.getName())
                        .password(EMPTY_PASSWORD)
                        .email(userInfo.getEmail())
                        .picture(userInfo.getPicture())
                        .role(Role.USER)
                        .providerType(providerType)
                        .build();

        return userRepository.save(user);
    }

    private User updateUser(User user, OAuth2UserInfo userInfo) {
        // TODO 추가하기
        return user;
    }
}
