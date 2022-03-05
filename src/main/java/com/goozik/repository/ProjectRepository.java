package com.goozik.repository;

import com.goozik.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ryu
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
