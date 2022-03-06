package com.goozik.controller;

import com.goozik.annotation.LoginUser;
import com.goozik.model.entity.vo.SessionUser;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping
    public String getUser(@LoginUser SessionUser user) {
        log.info(user.toString());

        return "success";
    }
}
