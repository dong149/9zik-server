package com.goozik.server.model.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.goozik.model.entity.Project;
import com.goozik.model.entity.User;
import java.time.LocalDateTime;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BaseTimeEntityTest {

    @Mock
    private User user;

    @Mock
    private Project project;

    private final LocalDateTime NOW = LocalDateTime.of(
        2022,6,4
        ,13,22,50
    );

    private final LocalDateTime PROJECT_CREATED_AT = LocalDateTime.of(
        2022,6,4
        ,17,6,11
    );

    private final LocalDateTime REFERENCE_DATE_STARTED_AT = LocalDateTime.of(
        2022,1,1
        ,0,0,0
    );

    @BeforeClass
    public void initialization() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("유저 생성시 createAt/updateAt 값 검증")
    void testUserTimeWhenCreateUser() {
        assertTrue(user != null);

        when(user.getCreatedAt()).thenReturn(NOW);
        when(user.getUpdatedAt()).thenReturn(NOW);

        assertAll(
            () ->
                assertTrue(user.getCreatedAt().isAfter(REFERENCE_DATE_STARTED_AT)),

            () ->
                assertTrue(user.getUpdatedAt().isAfter(REFERENCE_DATE_STARTED_AT)),

            () ->
                assertTrue(user.getCreatedAt().isEqual(user.getUpdatedAt()))
        );
    }

    @Test
    @DisplayName("프로젝트 생성시 createAt/updateAt 값 검증")
    void testProjectTimeCreated() {
        assertTrue(user != null);
        assertTrue(project != null);

        when(project.getCreatedAt()).thenReturn(PROJECT_CREATED_AT);
        when(project.getUpdatedAt()).thenReturn(PROJECT_CREATED_AT);
        when(user.getCreatedAt()).thenReturn(NOW);

        assertAll(
            () ->
                assertTrue(project.getCreatedAt().isAfter(user.getCreatedAt())),

            () ->
                assertTrue(project.getUpdatedAt().isAfter(user.getCreatedAt())),

            () ->
                assertTrue(project.getCreatedAt().isEqual(project.getUpdatedAt()))
        );
    }
}