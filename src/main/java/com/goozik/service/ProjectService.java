package com.goozik.service;

import com.goozik.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Project Service
 *
 * @author ryu
 */
@Service
public class ProjectService {

    public Page<Project> getProjects(int size, int page) {
        return null;
    }
}
