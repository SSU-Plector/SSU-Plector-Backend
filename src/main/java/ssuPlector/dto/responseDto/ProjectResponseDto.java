package ssuPlector.dto.responseDto;

import lombok.Getter;
import ssuPlector.domain.Project;

@Getter
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String imagePath;
    private String shortIntro;
    private String category;
    private long hits;

    public ProjectResponseDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.imagePath = project.getName();
        this.shortIntro = project.getShortIntro();
        this.category = project.getCategory().name();
        this.hits = project.getHits();
    }
}
