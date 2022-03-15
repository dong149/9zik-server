package com.goozik.controller;

import com.goozik.model.dto.UserDto;
import com.goozik.service.UserService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public UserDto.Response getUser(HttpServletRequest request) {
        String email = request.getUserPrincipal().getName();

        log.info("user email : {}", email);
//
        return userService.getUser(email);
    }
}
