package ssuPlector.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssuPlector.domain.Image;
import ssuPlector.domain.Project;
import ssuPlector.domain.category.Category;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.TechStack;
import ssuPlector.dto.response.DeveloperDTO.DeveloperPreviewDTO;

public class ProjectDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectPreviewDTO {
        Long id;
        String name;
        String imageLink;
        String shortIntro;
        Category category;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectDetailDTO {
        Long id;
        String name;
        String imageLink;
        List<DeveloperPreviewDTO> developerList;
        String shortIntro;
        String longIntro;
        Category category;
        long hits;
        String githubLink;
        String infoPageLink;
        String webLink;
        String appLink;
        List<DevLanguage> languageList;
        List<DevTools> devToolList;
        List<TechStack> techStackList;
    }

    @Getter
    public static class ProjectListResponseDto {

        private int currentElement; // 현재 페이지 아이템 개수
        private int totalPage; // 전체 페이지
        private long totalElement; // 전체 아이템 개수
        private List<ProjectResponseDto> projectResponseDtoList;

        public ProjectListResponseDto(Page<Project> projectPage) {
            List<ProjectResponseDto> projectResponseDtoList =
                    projectPage.getContent().stream()
                            .map(ProjectResponseDto::new)
                            .collect(Collectors.toList());
            this.projectResponseDtoList = projectResponseDtoList;
            this.totalPage = projectPage.getTotalPages();
            this.currentElement = projectPage.getNumberOfElements();
            this.totalElement = projectPage.getTotalElements();
        }
    }

    @Getter
    public static class ProjectResponseDto {
        private Long id;
        private String name;
        private String imagePath;
        private String shortIntro;
        private String category;
        private long hits;

        public ProjectResponseDto(Project project) {
            this.id = project.getId();
            this.name = project.getName();
            if (project.getImageList() == null) this.imagePath = null;
            else
                for (Image image : project.getImageList())
                    if (image.getIsMainImage()) this.imagePath = image.getImagePath();
            this.shortIntro = project.getShortIntro();
            this.category = project.getCategory().name();
            this.hits = project.getHits();
        }
    }
}
