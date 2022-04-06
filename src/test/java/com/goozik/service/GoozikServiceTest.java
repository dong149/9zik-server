package com.goozik.service;

import com.goozik.model.entity.Project;
import com.goozik.model.entity.User;
import java.util.List;

public class GoozikServiceTest {

    protected static final int FIRST = 0;
    protected static final String TEST_EMAIL = "test@test.com";
    protected static final List<String> TEST_TITLES = List.of("test1", "test2");

    protected final User testUser = User.builder().name("test").email(TEST_EMAIL).build();
    protected final List<Project> testProjects = List.of(
        Project.builder().user(testUser).title(TEST_TITLES.get(0)).build(),
        Project.builder().user(testUser).title(TEST_TITLES.get(1)).build());
}
