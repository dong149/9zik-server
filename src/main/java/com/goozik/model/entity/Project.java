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

    private static final int DEFAULT_LIKE_COUNT = 0;

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String picture;
    @Column
    private String gif;
    @Column
    private Integer likeCount;
    @Column
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;
    @Column
    private String createdBy;
    @Column
    private LocalDateTime deletedAt;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Builder
    public Project(
        String title,
        String description,
        Integer likeCount,
        ProjectType projectType,
        User user) {

        Objects.requireNonNull(user, "user must not be null");

        this.title = title;
        this.description = description;
        this.likeCount = likeCount;
        this.projectType = projectType;
        this.user = user;
    }

    public static Project of(ProjectDto.Request request, User user) {
        return Project.builder()
                      .title(request.getTitle())
                      .description(request.getDescription())
                      .likeCount(request.getLikeCount())
                      .projectType(request.getProjectType())
                      .user(user)
                      .build();
    }
}
