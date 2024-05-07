package ssuPlector.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
        String imageLink;
        List<ProjectPreviewDTO> projectList;
        List<DevLanguage> languageList;
        List<DevTools> devToolList;
        List<TechStack> techStackList;
        Part part1;
        Part part2;
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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeveloperResponseDTO {
        Long id;
        String name;
        Part part1;
        Part part2;
        String githubLink;
        Long hits;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeveloperListResponseDTO {
        int currentElement; // 현재 페이지 아이템 개수
        int totalPage; // 전체 페이지
        long totalElement; // 전체 아이템 개수
        List<DeveloperResponseDTO> developerResponseDTOList;
    }
}
