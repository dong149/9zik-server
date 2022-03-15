package com.goozik.model.dto;

import com.goozik.model.constants.Role;
import com.goozik.model.entity.User;
import lombok.Builder;
import lombok.Getter;

public class UserDto {

    @Getter
    @Builder
    public static class Response {

        private String name;
        private String email;
        private String picture;
        private Role role;

        public static Response from(User user) {
            return UserDto.Response.builder()
                                   .name(user.getName())
                                   .email(user.getEmail())
                                   .picture(user.getPicture())
                                   .role(user.getRole())
                                   .build();
        }
    }
}
