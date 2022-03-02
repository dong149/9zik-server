package com.goozik.repository;

import com.goozik.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project Repository
 *
 * @author ryu
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

}