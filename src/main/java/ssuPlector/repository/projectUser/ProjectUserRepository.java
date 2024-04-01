package ssuPlector.repository.projectUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ssuPlector.domain.ProjectUser;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {
    List<ProjectUser> findByProject_Id(Long projectId);
}
