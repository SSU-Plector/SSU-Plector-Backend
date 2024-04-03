package ssuPlector.converter;

import static ssuPlector.dto.request.ImageDTO.*;

import org.springframework.stereotype.Component;

import ssuPlector.domain.Image;
import ssuPlector.dto.response.ImageDTO.ImagePreviewDTO;

@Component
public class ImageConverter {
    public static ImagePreviewDTO toImagePreviewDTO(Image image) {
        return ImagePreviewDTO.builder().imagePath(image.getImagePath()).build();
    }

    public static Image toImage(ImageRequestDTO requestDTO) {
        return Image.builder().imagePath(requestDTO.getImagePath()).build();
    }
}
