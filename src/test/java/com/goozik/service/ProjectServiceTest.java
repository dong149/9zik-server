package com.goozik.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.goozik.model.dto.ProjectDto;
import com.goozik.model.entity.Project;
import com.goozik.repository.ProjectRepository;
import com.goozik.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest extends GoozikServiceTest {

    @InjectMocks
    ProjectService projectService;

    @Mock
    ProjectRepository projectRepository;
    @Mock
    UserRepository userRepository;

    private final ProjectDto.Request testRequest = ProjectDto.Request.builder()
                                                                     .title(TITLES.get(0))
                                                                     .description(DESCRIPTIONS.get(0))
                                                                     .email(EMAIL)
                                                                     .projectType(PROJECT_TYPES.get(0))
                                                                     .build();

    @Test
    @DisplayName("project findAll 성공 확인")
    void getProjects() {
        // given
        Pageable testPageable = PageRequest.of(0, 50);
        given(projectRepository.findAll(testPageable))
            .willReturn(new PageImpl<>(
                testProjects,
                testPageable,
                testProjects.size()));

        // when
        List<ProjectDto.Response> actualResponses = projectService.getProjects(testPageable).getContent();

        // then
        then(projectRepository).should().findAll(testPageable);
        assertAll(
            () -> assertThat(actualResponses).hasSize(testProjects.size()),
            () -> assertThat(actualResponses.get(FIRST_IDX).getTitle()).isEqualTo(TITLES.get(FIRST_IDX)));
    }

    @Test
    @DisplayName("project 생성 확인")
    void createProject() {
        // given
        given(userRepository.findByEmail(EMAIL)).willReturn(Optional.ofNullable(testUser));

        // when
        projectService.createProject(testRequest);

        // then
        then(projectRepository).should().save(any(Project.class));
    }

    @Test
    @DisplayName("project 생성시, 존재하지 않는 유저일경우 예외 발생 확인")
    void createProjectsThrow() {
        // given
        given(userRepository.findByEmail(EMAIL)).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> projectService.createProject(testRequest))
            .isInstanceOf(EntityNotFoundException.class);
    }
}