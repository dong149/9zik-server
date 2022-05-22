package com.goozik.service;

import com.goozik.model.dto.ProjectDto;
import com.goozik.model.entity.Project;
import com.goozik.model.entity.User;
import com.goozik.repository.ProjectRepository;
import com.goozik.repository.UserRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ProjectDto.Response getProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("user not found");
        });

        return ProjectDto.Response.from(project);
    }

    @Transactional(readOnly = true)
    public Page<ProjectDto.Response> getProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).map(ProjectDto.Response::from);
    }

    @Transactional
    public void createProject(ProjectDto.Request request) {
        User user = getUserByEmail(request.getEmail());
        Project project = Project.of(request, user);

        projectRepository.save(project);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw new EntityNotFoundException("user not found");
        });
    }
}
