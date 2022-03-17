package com.goozik.service;

import com.goozik.model.dto.UserDto;
import com.goozik.model.entity.User;
import com.goozik.repository.UserRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDto.Response getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });

        return UserDto.Response.from(user);
    }
}
