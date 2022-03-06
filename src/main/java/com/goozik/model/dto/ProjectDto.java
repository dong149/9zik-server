package com.goozik.model.dto;

import com.goozik.model.constants.ProjectType;
import com.goozik.model.entity.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ProjectDto {

    private static final int DEFAULT_LIKE_COUNT = 0;

    @Getter
    @ToString
    @NoArgsConstructor
    public static class Request {

        private String title;
        private String description;
        private ProjectType projectType;

        public static Project convertToEntity(Request request) {
            return Project.builder()
                .title(request.title)
                .description(request.description)
                .likeCount(DEFAULT_LIKE_COUNT)
                .projectType(request.projectType)
                .build();
        }
    }
}
