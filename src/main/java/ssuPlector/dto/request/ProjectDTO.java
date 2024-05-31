package ssuPlector.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ssuPlector.domain.category.Category;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.Part;
import ssuPlector.domain.category.TechStack;
import ssuPlector.validation.annotation.MaxSizeThree;
import ssuPlector.validation.annotation.MaxSizeTwo;

public class ProjectDTO {
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProjectListRequestDto {
        private String searchString;
        private String category;
        private String sortType; // recent, old, high, low
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProjectRequestDTO {

        @NotBlank(message = "필수 입력값")
        @Size(max = 30, message = "최대 30자")
        private String name;

        @NotBlank(message = "필수 입력값")
        @Size(max = 101, message = "최대 100자")
        private String shortIntro;

        @NotBlank(message = "필수 입력값")
        @Size(max = 500, message = "최대 500자")
        private String longIntro;

        private String githubLink;

        private String infoPageLink;

        private String webLink;

        private String appLink;

        private Category category;

        @MaxSizeThree private List<DevLanguage> languageList;
        @MaxSizeThree private List<DevTools> devToolList;
        @MaxSizeThree private List<TechStack> techStackList;

        private List<ProjectDeveloperRequestDTO> projectDevloperList;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProjectDeveloperRequestDTO {

        @NotBlank(message = "필수 입력값")
        @Size(max = 20, message = "최대 20자")
        private String name;

        private String email; // 계정 존재 여부 확인
        @MaxSizeTwo private List<Part> partList;

        @NotNull private boolean isTeamLeader;

        public boolean getIsTeamLeader() {
            return isTeamLeader;
        }
    }
}
