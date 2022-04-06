package com.goozik.model.entity;

import com.goozik.model.constants.ProjectType;
import com.goozik.model.dto.ProjectDto;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column
    private String picture;
    @Column
    private String gif;
    @Column
    private int likeCount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;
    @Column(nullable = false)
    private Long userIdx;
    @Column
    private LocalDateTime deletedAt;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Builder
    public Project(
        String title,
        String description,
        int likeCount,
        ProjectType projectType,
        Long userIdx,
        User user) {

        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(projectType, "projectType must not be null");
        Objects.requireNonNull(user, "user must not be null");

        this.title = title;
        this.description = description;
        this.likeCount = likeCount;
        this.projectType = projectType;
        this.userIdx = userIdx;
        this.user = user;
    }

    public static Project of(ProjectDto.Request request, User user) {
        return Project.builder()
                      .title(request.getTitle())
                      .description(request.getDescription())
                      .projectType(request.getProjectType())
                      .userIdx(user.getId())
                      .user(user)
                      .build();
    }
}
