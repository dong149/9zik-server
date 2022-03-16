package com.goozik.service;

import com.goozik.model.dto.ProjectDto;
import com.goozik.model.entity.Project;
import com.goozik.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public Page<ProjectDto.Response> getProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).map(ProjectDto.Response::from);
    }

    public void createProject(ProjectDto.Request request) {
        Project project = ProjectDto.Request.convertToEntity(request);

        projectRepository.save(project);
    }
}
