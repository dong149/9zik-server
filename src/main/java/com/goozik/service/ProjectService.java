package com.goozik.service;

import com.goozik.model.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author ryu
 */
@Service
public class ProjectService {

    public Page<Project> getProjects(Pageable pageable) {
        return null;
    }
}
