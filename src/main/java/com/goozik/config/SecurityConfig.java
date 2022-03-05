package com.goozik.config;

import com.goozik.model.constants.Role;
import com.goozik.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ryu
 */
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
        "/swagger/**"};

    private static final String[] PERMIT_ANT_PATTERNS = {
        "/",
        "/api/v1/project",
        "/h2-console/**",
        "/swagger-ui/**",
        "/swagger-resources/**"};

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(WEB_IGNORE_ANT_PATTERNS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable().headers().frameOptions().disable().and()
            .authorizeRequests()
            .antMatchers(PERMIT_ANT_PATTERNS).permitAll()
            .antMatchers("/api/v1/**").hasRole(Role.GUEST.name())
            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService);
    }
}
