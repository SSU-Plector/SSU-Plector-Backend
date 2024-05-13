package ssuPlector.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ssuPlector.domain.Project;
import ssuPlector.domain.ProjectDeveloper;
import ssuPlector.dto.request.ProjectDTO;
import ssuPlector.dto.response.ProjectDTO.ProjectDetailDTO;
import ssuPlector.dto.response.ProjectDTO.ProjectPreviewDTO;

@Component
public class ProjectConverter {

    public static ProjectPreviewDTO toProjectPreviewDTO(ProjectDeveloper projectDeveloper) {
        Project project = projectDeveloper.getProject();

        return ProjectPreviewDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .shortIntro(project.getShortIntro())
                .category(project.getCategory())
                .imageLink(project.getImageList().get(0).getImagePath())
                .build();
    }

    public static ProjectDetailDTO toProjectDetailDTO(Project project) {
        return ProjectDetailDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .imageLink(project.getImageList().get(0).getImagePath())
                .hits(project.getHits())
                .githubLink(project.getGithubLink())
                .developerList(
                        project.getProjectDeveloperList().stream()
                                .map(DeveloperConverter::toDeveloperPreviewDTO)
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

    public static Project toProject(ProjectDTO.ProjectRequestDTO requestDTO) {
        return Project.builder()
                .name(requestDTO.getName())
                .shortIntro(requestDTO.getShortIntro())
                .longIntro(requestDTO.getLongIntro())
                .infoPageLink(requestDTO.getInfoPageLink())
                .githubLink(requestDTO.getGithubLink())
                .webLink(requestDTO.getWebLink())
                .appLink(requestDTO.getAppLink())
                .category(requestDTO.getCategory())
                .languageList(requestDTO.getLanguageList())
                .devToolList(requestDTO.getDevToolList())
                .techStackList(requestDTO.getTechStackList())
                .build();
    }
}
