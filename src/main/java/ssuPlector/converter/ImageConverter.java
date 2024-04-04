package ssuPlector.converter;

import org.springframework.stereotype.Component;

import ssuPlector.domain.Image;
import ssuPlector.dto.response.ImageDTO.ImagePreviewDTO;

@Component
public class ImageConverter {
    public static ImagePreviewDTO toImagePreviewDTO(Image image) {
        return ImagePreviewDTO.builder().imagePath(image.getImagePath()).build();
    }

    public static Image toImage(String imagePath) {
        return Image.builder().imagePath(imagePath).build();
    }
}
