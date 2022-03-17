package com.goozik.model.dto;

import com.goozik.model.constants.ProjectType;
import com.goozik.model.entity.Project;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ProjectDto {

    @Getter
    @ToString
    @NoArgsConstructor
    public static class Request {

        private String title;
        private String description;
        private String email;
        private ProjectType projectType;
        private int likeCount;
    }

    @Getter
    @Builder
    public static class Response {

        private Long id;
        private String title;
        private String description;
        private String picture;
        private Integer likeCount;
        private ProjectType projectType;
        private LocalDateTime createdAt;

        public static Response from(Project project) {
            return Response.builder()
                           .id(project.getId())
                           .title(project.getTitle())
                           .description(project.getDescription())
                           .likeCount(project.getLikeCount())
                           .projectType(project.getProjectType())
                           .createdAt(project.getCreatedAt())
                           .build();
        }
    }
}
