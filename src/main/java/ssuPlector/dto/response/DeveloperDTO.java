package ssuPlector.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssuPlector.domain.Image;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.Part;
import ssuPlector.domain.category.TechStack;
import ssuPlector.dto.response.ProjectDTO.ProjectPreviewDTO;

public class DeveloperDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeveloperDetailDTO {
        Long id;
        String name;
        String shortIntro;
        String university;
        String major;
        String studentNumber;
        String email;
        Long hits;
        String kakaoId;
        String githubLink;
        boolean isDeveloper;
        List<String> linkList;
        List<Image> imageList;
        List<ProjectPreviewDTO> projectList;
        List<DevLanguage> languageList;
        List<DevTools> devToolList;
        List<TechStack> techStackList;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeveloperPreviewDTO {
        Long id;
        String name;
        List<Part> partList;
    }
}
