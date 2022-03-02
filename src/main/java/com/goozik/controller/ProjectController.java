package com.goozik.controller;

import com.goozik.entity.Project;
import com.goozik.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project Controller
 *
 * @author ryu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public Page<Project> getProjects(@RequestParam int size, @RequestParam int page) {
        return projectService.getProjects(size, page);
    }

    @PostMapping
    public void createProject(@RequestBody Object object) {
    }
}
