package com.goozik.model.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.goozik.model.constants.Role;
import com.goozik.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BaseEntityTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("BaseTimeEntity createAt, updateAt 생성 및 정확한 시간 생성 여부 확인")
    void testCreateUpdateTime() {
        // given
        final LocalDateTime REFERENCE_DATE_NOW = LocalDateTime.now();

        // when
        final User persistUser = User.builder().email("ekek").password("pwww").name("ssn")
            .nickName("billy").role(Role.USER).build();

        userRepository.save(persistUser);

        //then
        assertAll(
            () -> assertThat(persistUser.getCreatedAt()).isNotNull()
                .isAfter(REFERENCE_DATE_NOW),

            () -> assertThat(persistUser.getUpdatedAt()).isNotNull()
                .isAfter(REFERENCE_DATE_NOW)
        );
    }

    @Test
    @DisplayName("사용자 정보 변경시 updateAt 변경 확인")
    void testUpdateTimeWhenModified() throws InterruptedException {
        // given
        final User user = User.builder().email("email").password("password").name("name")
            .nickName("jack").role(Role.USER).build();

        userRepository.save(user);
        LocalDateTime beforeUpdate = user.getUpdatedAt();

        Thread.sleep(500);
        // when
        Optional<User> updatedUser = userRepository.findByEmail("email");
        assertThat(updatedUser).isNotNull();

        updatedUser.get().update("aim", "hack");
        entityManager.flush();
        entityManager.clear();

        LocalDateTime afterUpdate = updatedUser.get().getUpdatedAt();

        // then
        assertThat(afterUpdate).isAfter(beforeUpdate);
    }
}
