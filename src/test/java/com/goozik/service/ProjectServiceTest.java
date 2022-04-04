package com.goozik.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.goozik.model.dto.ProjectDto;
import com.goozik.model.entity.Project;
import com.goozik.model.entity.User;
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
class ProjectServiceTest {

    @InjectMocks
    ProjectService projectService;

    @Mock
    ProjectRepository projectRepository;
    @Mock
    UserRepository userRepository;

    private static final String TEST_EMAIL = "test@test.com";
    private final User testUser = User.builder().name("test").email(TEST_EMAIL).build();
    private final ProjectDto.Request testRequest = ProjectDto.Request.builder().email(TEST_EMAIL).build();
    private final List<Project> testProjects = List.of(
        Project.builder().user(testUser).title("test1").build(),
        Project.builder().user(testUser).title("test2").build());

    @Test
    @DisplayName("get 잘동작하는지 확인")
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
        assertThat(actualResponses).hasSize(2);
        assertThat(actualResponses.get(0).getTitle()).isEqualTo("test1");
    }

    @Test
    @DisplayName("post 잘동작하는지 확인")
    void createProject() {
        // given
        given(userRepository.findByEmail(TEST_EMAIL)).willReturn(Optional.ofNullable(testUser));

        // when
        projectService.createProject(testRequest);

        // then
        then(projectRepository).should().save(any(Project.class));
    }

    @Test
    @DisplayName("post시 에러 발생")
    void createProjectsThrow() {
        // given
        given(userRepository.findByEmail(TEST_EMAIL)).willReturn(Optional.empty());

        // when
        Throwable throwable = catchThrowable(() -> projectService.createProject(testRequest));

        // then
        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);
    }
}