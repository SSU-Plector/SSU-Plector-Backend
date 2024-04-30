package ssuPlector.repository.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ssuPlector.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {
    @Modifying
    @Query("UPDATE Project p SET p.hits = :hits WHERE p.id = :id")
    void updateHitsById(@Param("id") Long id, @Param("hits") Long hits);

    List<Project> findAllByIdIn(List<Long> id);
}
