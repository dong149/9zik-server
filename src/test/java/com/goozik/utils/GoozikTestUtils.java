package com.goozik.utils;

import com.goozik.model.constants.ProjectType;
import com.goozik.model.entity.Project;
import com.goozik.model.entity.User;
import java.util.List;

public class GoozikTestUtils {
    
    public static final int FIRST_IDX = 0;
    public static final String EMAIL = "test@test.com";
    public static final String NON_EXIST_EMAIL = "no@no.com";
    public static final List<String> TITLES = List.of("test1", "test2");
    public static final List<String> DESCRIPTIONS = List.of("test description1", "test description2");
    public static final List<ProjectType> PROJECT_TYPES = List.of(ProjectType.TOY, ProjectType.BUSINESS);
    
    public static final User TEST_USER = User.builder().name("test").email(EMAIL).build();
    public static final List<Project> TEST_PROJECTS = List.of(
        Project.builder()
               .user(TEST_USER)
               .title(TITLES.get(0))
               .description(DESCRIPTIONS.get(0))
               .projectType(PROJECT_TYPES.get(0))
               .build(),
        Project.builder()
               .user(TEST_USER)
               .title(TITLES.get(1))
               .description(DESCRIPTIONS.get(1))
               .projectType(PROJECT_TYPES.get(1))
               .build());
}
