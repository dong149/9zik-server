package com.goozik.controller;

import com.goozik.model.entity.Project;
import com.goozik.service.ProjectService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ryu
 */
@Api
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public Page<Project> getProjects(@RequestParam int page, @RequestParam int size) {
        return projectService.getProjects(PageRequest.of(page, size));
    }

    @PostMapping
    public void createProject(@RequestBody Object object) {
    }
}
