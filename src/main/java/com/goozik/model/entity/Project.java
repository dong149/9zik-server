package com.goozik.model.entity;

import com.goozik.model.constants.ProjectType;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 프로젝트
 *
 * @author ryu
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    /**
     * id
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 프로젝트 타입
     */
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;
    /**
     * 생성자
     */
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
}
