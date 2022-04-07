package com.goozik.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.goozik.model.dto.UserDto;
import com.goozik.repository.UserRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest extends GoozikServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("email로 user 조회 성공 확인")
    void getUser() {
        // given
        given(userRepository.findByEmail(EMAIL)).willReturn(Optional.ofNullable(testUser));

        // when
        UserDto.Response actualResponse = userService.getUser(EMAIL);

        // then
        then(userRepository).should().findByEmail(EMAIL);
        assertThat(actualResponse.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    @DisplayName("user 조회시, 존재하지 않는 유저일경우 예외 발생 확인")
    void getUserThrow() {
        // given
        given(userRepository.findByEmail(NON_EXIST_EMAIL)).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> userService.getUser(NON_EXIST_EMAIL))
            .isInstanceOf(EntityNotFoundException.class);
    }
}