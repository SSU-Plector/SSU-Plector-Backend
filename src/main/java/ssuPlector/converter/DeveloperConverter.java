package ssuPlector.converter;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import ssuPlector.domain.Developer;
import ssuPlector.domain.ProjectDeveloper;
import ssuPlector.dto.request.DeveloperDTO.DeveloperRequestDTO;
import ssuPlector.dto.response.DeveloperDTO;
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
                .imageLink(developer.getImageList().get(0).getImagePath())
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

    public static DeveloperDTO.DeveloperListResponseDTO toDeveloperResponseListDTO(
            Page<Developer> developerList) {
        return DeveloperDTO.DeveloperListResponseDTO.builder()
                .currentPage(developerList.getNumber())
                .pageSize(developerList.getSize())
                .totalPage(developerList.getTotalPages())
                .currentElement(developerList.getNumberOfElements())
                .totalElement(developerList.getTotalElements())
                .developerResponseDTOList(
                        developerList.getContent().stream()
                                .map(DeveloperConverter::toDeveloperResponseDTO)
                                .collect(Collectors.toList()))
                .build();
    }

    public static DeveloperDTO.DeveloperResponseDTO toDeveloperResponseDTO(Developer developer) {
        return DeveloperDTO.DeveloperResponseDTO.builder()
                .id(developer.getId())
                .name(developer.getName())
                .githubLink(developer.getGithubLink())
                .hits(developer.getHits())
                .techStackList(developer.getTechStackList())
                .build();
    }

    public static Developer toDeveloper(DeveloperRequestDTO requestDTO) {
        return Developer.builder()
                .name(requestDTO.getName())
                .shortIntro(requestDTO.getShortIntro())
                .university(requestDTO.getUniversity())
                .major(requestDTO.getMajor())
                .studentNumber(requestDTO.getStudentNumber())
                .email(requestDTO.getEmail())
                .hits(0)
                .kakaoId(requestDTO.getKakaoId())
                .githubLink(requestDTO.getGithubLink())
                .isDeveloper(true)
                .languageList(requestDTO.getLanguageList())
                .devToolList(requestDTO.getDevToolList())
                .techStackList(requestDTO.getTechStackList())
                .build();
    }
}
