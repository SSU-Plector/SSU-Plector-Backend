package ssuPlector.dto.request;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ImageDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ImageRequestDTO {

        private String imagePath;

        @NotNull private boolean isMainImage;

        public boolean getIsMainImage() {
            return isMainImage;
        }
    }
}
