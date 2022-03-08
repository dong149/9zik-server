package com.goozik.controller;

import com.goozik.model.dto.ProjectDto;
import com.goozik.model.entity.Project;
import com.goozik.service.ProjectService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public Page<Project> getProjects(
        @ApiParam(required = true, defaultValue = "0") @RequestParam int page,
        @ApiParam(required = true, defaultValue = "50") @RequestParam int size) {

        log.info("get projects page : {}, size : {}", page, size);

        return projectService.getProjects(PageRequest.of(page, size));
    }

    @PostMapping
    public void createProject(@RequestBody ProjectDto.Request request) {
        log.info("create project request : {}", request);

        projectService.createProject(request);
    }
}
