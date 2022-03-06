package com.goozik.model.entity;

import com.goozik.model.constants.ProjectType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

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
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column
    private LocalDateTime deletedAt;

    @Builder
    public Project(String title, String description, Integer likeCount, ProjectType projectType) {
        this.title = title;
        this.description = description;
        this.likeCount = likeCount;
        this.projectType = projectType;
    }
}
