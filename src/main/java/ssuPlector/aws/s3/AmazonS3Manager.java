package ssuPlector.aws.s3;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssuPlector.config.AmazonConfig;
import ssuPlector.domain.Uuid;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.repository.UuidRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager {

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;

    public String uploadFile(String keyName, MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        String originalFilename = file.getOriginalFilename();
        String extension =
                originalFilename != null
                        ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1)
                        : null;

        if ("png".equalsIgnoreCase(extension)) {
            metadata.setContentType(MediaType.IMAGE_PNG_VALUE);
        } else if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
            metadata.setContentType(MediaType.IMAGE_JPEG_VALUE);
        } else if ("gif".equalsIgnoreCase(extension)) {
            metadata.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            metadata.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        }

        try {
            amazonS3.putObject(
                    new PutObjectRequest(
                            amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        } catch (IOException e) {
            log.error("error at AmazonS3Manager uploadFile : {}", (Object) e.getStackTrace());
            throw new GlobalException(GlobalErrorCode._BAD_REQUEST);
        }

        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

    public String generateDeveloperKeyName(Uuid uuid) {
        return amazonConfig.getDeveloperPath() + '/' + uuid.getUuid();
    }

    public String generateProjectKeyName(Uuid uuid) {
        return amazonConfig.getProjectPath() + '/' + uuid.getUuid();
    }
}
