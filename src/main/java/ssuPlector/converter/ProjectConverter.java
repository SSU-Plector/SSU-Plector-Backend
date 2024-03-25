package ssuPlector.converter;

import org.springframework.stereotype.Component;

import ssuPlector.domain.Project;
import ssuPlector.domain.ProjectUser;
import ssuPlector.dto.Response.ProjectDTO.ProjectPreviewDTO;

@Component
public class ProjectConverter {
    public static ProjectPreviewDTO toProjectPreviewDTO(ProjectUser projectuser) {
        Project project = projectuser.getProject();

        return ProjectPreviewDTO.builder()
                .id(project.getId())
                .hits(project.getHits())
                .name(project.getName())
                .shortIntro(project.getShortIntro())
                .category(project.getCategory())
                .build();
    }
}
