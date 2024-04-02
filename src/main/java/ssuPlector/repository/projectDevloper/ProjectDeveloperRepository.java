package ssuPlector.repository.projectDevloper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ssuPlector.domain.ProjectDeveloper;

@Repository
public interface ProjectDeveloperRepository extends JpaRepository<ProjectDeveloper, Long> {
    List<ProjectDeveloper> findByProject_Id(Long projectId);
}
