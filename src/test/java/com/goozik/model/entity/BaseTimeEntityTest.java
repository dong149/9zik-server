package com.goozik.model.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.goozik.model.constants.Role;
import com.goozik.model.entity.User.UserBuilder;
import com.goozik.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BaseTimeEntityTest {

    private static final LocalDateTime REFERENCE_DATE_NOW = LocalDateTime.of(
        2022, 1, 1
        , 0, 0, 0
    );

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("BaseTimeEntity createAt, updateAt 생성 확인")
    void testCreateUpdateTime() {
        // given
        userRepository.save(
            new User.UserBuilder().email("email").password("password").name("name").nickName("nick")
                .role(Role.USER).build());

        // when
        List<User> users = userRepository.findAll();

        //then
        User user = users.get(0);

        assertAll(
            () -> assertThat(user.getCreatedAt()).isNotNull().isAfter(REFERENCE_DATE_NOW),
            () -> assertThat(user.getUpdatedAt()).isNotNull().isAfter(REFERENCE_DATE_NOW)
        );

    }

    @Test
    @DisplayName("updateAt 변경 확인")
    void testUpdateTimeWhenModified() {
        // given
        User user = userRepository.save(
            new UserBuilder().email("email").password("password").name("name").nickName("nick")
                .role(Role.USER).build());

        LocalDateTime beforeUpdate = userRepository.save(user).getUpdatedAt();

        user.update("name", "pick");
        entityManager.flush();
        entityManager.clear();

        // when
        User updateUser = userRepository.findByEmail(user.getEmail())
            .orElseThrow(IllegalArgumentException::new);

        // then
        assertThat(updateUser.getUpdatedAt()).isNotNull().isAfter(beforeUpdate);
    }
}
