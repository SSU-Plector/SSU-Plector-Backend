package ssuPlector.dto.responseDto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import lombok.Getter;
import ssuPlector.domain.Image;
import ssuPlector.domain.Project;

@Getter
public class ProjectListResponseDto {
    private int currentPage; // 현재 페이지
    private int currentElement; // 현재 페이지 아이템 개수
    private int pageSize; // 페이지 사이즈
    private int totalPage; // 전체 페이지
    private long totalElement; // 전체 아이템 개수
    private List<ProjectResponseDto> projectResponseDtoList;

    public ProjectListResponseDto(Page<Project> projectPage) {
        List<ProjectResponseDto> projectResponseDtoList =
                projectPage.getContent().stream()
                        .map(ProjectResponseDto::new)
                        .collect(Collectors.toList());
        this.projectResponseDtoList = projectResponseDtoList;
        this.currentPage = projectPage.getNumber();
        this.pageSize = projectPage.getSize();
        this.totalPage = projectPage.getTotalPages();
        this.currentElement = projectPage.getNumberOfElements();
        this.totalElement = projectPage.getTotalElements();
    }

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
            for (Image image : project.getImageList())
                if (image.isMainImage()) this.imagePath = image.getImagePath();
            this.shortIntro = project.getShortIntro();
            this.category = project.getCategory().name();
            this.hits = project.getHits();
        }
    }
}
