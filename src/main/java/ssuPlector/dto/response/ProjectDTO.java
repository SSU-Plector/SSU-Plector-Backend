package ssuPlector.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssuPlector.domain.Image;
import ssuPlector.domain.category.Category;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.TechStack;
import ssuPlector.dto.response.DeveloperDTO.DeveloperPreviewDTO;
import ssuPlector.dto.response.ImageDTO.ImagePreviewDTO;

public class ProjectDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectPreviewDTO {
        Long id;
        String name;
        List<Image> imageList;
        String shortIntro;
        Category category;
        long hits;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectDetailDTO {
        Long id;
        String name;
        List<ImagePreviewDTO> imageList;
        List<DeveloperPreviewDTO> developerList;
        String shortIntro;
        String longIntro;
        Category category;
        long hits;
        String infoPageLink;
        String webLink;
        String appLink;
        List<DevLanguage> languageList;
        List<DevTools> devToolList;
        List<TechStack> techStackList;
    }
}
