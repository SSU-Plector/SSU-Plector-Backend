package ssuPlector.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.Part;
import ssuPlector.domain.category.TechStack;
import ssuPlector.validation.annotation.MaxSizeThree;

public class DeveloperDTO {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeveloperListRequestDTO {

        private String sortType; // recent, old, high, low
        private Part part; // 개발자 키워드 검색
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeveloperRequestDTO {

        @NotBlank(message = "필수 입력값")
        @Size(max = 100, message = "최대 100자")
        private String shortIntro;

        @NotBlank(message = "필수 입력값")
        @Size(max = 30, message = "최대 30자")
        private String university;

        @NotBlank(message = "필수 입력값")
        @Size(max = 30, message = "최대 30자")
        private String major;

        @NotBlank(message = "필수 입력값")
        @Size(max = 20, message = "최대 20자")
        private String studentNumber;

        @NotBlank(message = "필수 입력값")
        @Size(max = 20, message = "최대 20자")
        private String kakaoId;

        private String githubLink;
        private Part part1;
        private Part part2;
        @MaxSizeThree private List<DevLanguage> languageList;
        @MaxSizeThree private List<DevTools> devToolList;
        @MaxSizeThree private List<TechStack> techStackList;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeveloperUpdateRequestDTO {
        @NotBlank(message = "필수 입력값")
        @Size(max = 100, message = "최대 100자")
        private String shortIntro;

        @NotBlank(message = "필수 입력값")
        @Size(max = 30, message = "최대 30자")
        private String university;

        @NotBlank(message = "필수 입력값")
        @Size(max = 30, message = "최대 30자")
        private String major;

        @NotBlank(message = "필수 입력값")
        @Size(max = 20, message = "최대 20자")
        private String studentNumber;

        private String githubLink;
        private Part part1;
        private Part part2;
        @MaxSizeThree private List<DevLanguage> languageList;
        @MaxSizeThree private List<DevTools> devToolList;
        @MaxSizeThree private List<TechStack> techStackList;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DummyDeveloperRequestDTO {

        @NotBlank(message = "필수 입력값")
        @Size(max = 20, message = "최대 20자")
        private String name;

        @NotBlank(message = "필수 입력값")
        @Size(max = 52, message = "최대 52자")
        private String email;

        @NotBlank(message = "필수 입력값")
        @Size(max = 100, message = "최대 100자")
        private String shortIntro;

        @NotBlank(message = "필수 입력값")
        @Size(max = 30, message = "최대 30자")
        private String university;

        @NotBlank(message = "필수 입력값")
        @Size(max = 30, message = "최대 30자")
        private String major;

        @NotBlank(message = "필수 입력값")
        @Size(max = 20, message = "최대 20자")
        private String studentNumber;

        @NotBlank(message = "필수 입력값")
        @Size(max = 20, message = "최대 20자")
        private String kakaoId;

        private String githubLink;
        private Part part1;
        private Part part2;
        @MaxSizeThree private List<DevLanguage> languageList;
        @MaxSizeThree private List<DevTools> devToolList;
        @MaxSizeThree private List<TechStack> techStackList;
    }
}
