package ssuPlector.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ssuPlector.domain.Project;
import ssuPlector.domain.ProjectUser;
import ssuPlector.dto.response.ProjectDTO.ProjectDetailDTO;
import ssuPlector.dto.response.ProjectDTO.ProjectPreviewDTO;

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

    public static ProjectDetailDTO toProjectDetailDTO(Project project) {
        return ProjectDetailDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .imageList(project.getImageList())
                .hits(project.getHits())
                .developerList(
                        project.getProjectUserList().stream()
                                .map(UserConverter::toDeveloperPreviewDTO)
                                .collect(Collectors.toList()))
                .shortIntro(project.getShortIntro())
                .longIntro(project.getLongIntro())
                .category(project.getCategory())
                .infoPageLink(project.getInfoPageLink())
                .webLink(project.getWebLink())
                .appLink(project.getAppLink())
                .languageList(project.getLanguageList())
                .devToolList(project.getDevToolList())
                .techStackList(project.getTechStackList())
                .build();
    }
}
