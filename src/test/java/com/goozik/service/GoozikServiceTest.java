package com.goozik.service;

import com.goozik.model.constants.ProjectType;
import com.goozik.model.entity.Project;
import com.goozik.model.entity.User;
import java.util.List;

public abstract class GoozikServiceTest {

    protected static final int FIRST_IDX = 0;
    protected static final String EMAIL = "test@test.com";
    protected static final String NON_EXIST_EMAIL = "no@no.com";
    protected static final List<String> TITLES = List.of("test1", "test2");
    protected static final List<String> DESCRIPTIONS = List.of("test description1", "test description2");
    protected static final List<ProjectType> PROJECT_TYPES = List.of(ProjectType.TOY, ProjectType.BUSINESS);

    protected final User testUser = User.builder().name("test").email(EMAIL).build();
    protected final List<Project> testProjects = List.of(
        Project.builder()
               .user(testUser)
               .title(TITLES.get(0))
               .description(DESCRIPTIONS.get(0))
               .projectType(PROJECT_TYPES.get(0))
               .build(),
        Project.builder()
               .user(testUser)
               .title(TITLES.get(1))
               .description(DESCRIPTIONS.get(1))
               .projectType(PROJECT_TYPES.get(1))
               .build());
}
