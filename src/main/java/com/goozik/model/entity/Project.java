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

/**
 * 프로젝트
 *
 * @author ryu
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 타이틀
     */
    @Column
    private String title;
    /**
     * 설명
     */
    @Column
    private String description;
    /**
     * 대표 이미지 링크
     */
    @Column
    private String picture;
    /**
     * 대표 gif 링크
     */
    @Column
    private String gif;
    /**
     * 좋아요
     */
    @Column
    private Integer likeCount;
    /**
     * 프로젝트 타입
     */
    @Column
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;
    /**
     * 생성자
     */
    @Column
    private String createdBy;
    /**
     * 생성일
     */
    @CreationTimestamp
    private LocalDateTime createdAt;
    /**
     * 수정일
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    /**
     * 삭제일
     */
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
