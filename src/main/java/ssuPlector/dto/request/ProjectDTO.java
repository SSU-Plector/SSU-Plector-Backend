package ssuPlector.dto.request;

import jakarta.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProjectDTO {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class ProjectListRequestDto {
        @Nullable private String searchString;
        @Nullable private String category;
        @Nullable private String sortType; // recent, old, high, low
    }
}
