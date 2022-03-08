package com.goozik.config;

import com.goozik.model.constants.Role;
import com.goozik.security.CustomOAuth2UserService;
import com.goozik.security.HttpCookieOAuth2AuthorizationRequestRepository;
import com.goozik.security.OAuth2AuthenticationFailureHandler;
import com.goozik.security.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] WEB_IGNORE_ANT_PATTERNS = {
        "/static/css/**",
        "/static/js/**",
        "*.ico",
        "/v2/api-docs",
        "/configuration/ui",
        "/swagger-resources",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        "/swagger/**",
        "/actuator/**"};

    private static final String[] PERMIT_ANT_PATTERNS = {
        "/",
        "/api/v1/project",
        "/h2-console/**",
        "/swagger-ui/**",
        "/swagger-resources/**",
        "/actuator/**"};

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(WEB_IGNORE_ANT_PATTERNS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf()
            .disable()
            .formLogin()
            .disable();

        http.authorizeRequests()
            .antMatchers(PERMIT_ANT_PATTERNS).permitAll()
            .antMatchers("/api/v1/**").hasRole(Role.USER.name())
            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .oauth2Login()
            .authorizationEndpoint()
            .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
            .baseUri("/oauth2/authorization")
            .and()
            .redirectionEndpoint()
            .baseUri("/oauth2/callback/*")
            .and()
            .userInfoEndpoint()
            .userService(customOAuth2UserService)
            .and()
            .successHandler(oAuth2SuccessHandler)
            .failureHandler(oAuth2AuthenticationFailureHandler);
    }
//            .oauth2Login()
//            .authorizationEndpoint()
//            .baseUri("/oauth2/authorize")
//            .authorizationRequestRepository(cookieAuthorizationRequestRepository())
//            .and()
//            .redirectionEndpoint()
//            .baseUri("/oauth2/callback/*")
//            .and()
//            .userInfoEndpoint()
//            .userService(customOAuth2UserService)
//            .and()
//            .successHandler(oAuth2AuthenticationSuccessHandler)
//            .failureHandler(oAuth2AuthenticationFailureHandler);
//            .oauth2Login()
//            .userInfoEndpoint()
//            .userService(customOAuth2UserService);
}
