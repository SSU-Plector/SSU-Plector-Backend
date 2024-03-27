package ssuPlector.repository.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ssuPlector.domain.Project;

public interface ProjectRepositoryCustom {
    Page<Project> findProjects(
            String searchString, String category, String sortType, Pageable pageable);
}
