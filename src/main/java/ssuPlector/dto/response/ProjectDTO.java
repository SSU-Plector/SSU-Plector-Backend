package ssuPlector.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssuPlector.domain.Image;
import ssuPlector.domain.category.Category;

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
}
