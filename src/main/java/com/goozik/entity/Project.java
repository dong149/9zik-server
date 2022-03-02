package com.goozik.entity;

import com.goozik.entity.constants.ProjectType;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 프로젝트
 *
 * @author ryu
 */
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
