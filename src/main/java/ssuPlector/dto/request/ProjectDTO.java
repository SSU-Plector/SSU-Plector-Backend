package ssuPlector.dto.request;

import jakarta.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProjectDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProjectListRequestDto {
        @Nullable private String searchString;
        @Nullable private String category;
        @Nullable private String sortType; // recent, old, high, low
    }
}
