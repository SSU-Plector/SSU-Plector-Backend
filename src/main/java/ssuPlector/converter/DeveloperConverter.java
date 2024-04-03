package ssuPlector.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ssuPlector.domain.Developer;
import ssuPlector.domain.ProjectDeveloper;
import ssuPlector.dto.response.DeveloperDTO.DeveloperDetailDTO;
import ssuPlector.dto.response.DeveloperDTO.DeveloperPreviewDTO;

@Component
public class DeveloperConverter {
    public static DeveloperDetailDTO toDeveloperDetailDTO(Developer developer) {
        return DeveloperDetailDTO.builder()
                .id(developer.getId())
                .name(developer.getName())
                .shortIntro(developer.getShortIntro())
                .university(developer.getUniversity())
                .major(developer.getMajor())
                .studentNumber(developer.getStudentNumber())
                .email(developer.getEmail())
                .hits(developer.getHits())
                .kakaoId(developer.getKakaoId())
                .githubLink(developer.getGithubLink())
                .isDeveloper(developer.getIsDeveloper())
                .imageList(
                        developer.getImageList().stream()
                                .map(ImageConverter::toImagePreviewDTO)
                                .collect(Collectors.toList()))
                .languageList(developer.getLanguageList())
                .devToolList(developer.getDevToolList())
                .techStackList(developer.getTechStackList())
                .projectList(
                        developer.getProjectDeveloperList().stream()
                                .map(ProjectConverter::toProjectPreviewDTO)
                                .collect(Collectors.toList()))
                .build();
    }

    public static DeveloperPreviewDTO toDeveloperPreviewDTO(ProjectDeveloper projectDeveloper) {
        return DeveloperPreviewDTO.builder()
                .id(projectDeveloper.getId())
                .name(projectDeveloper.getName())
                .partList(projectDeveloper.getPartList())
                .build();
    }
}
