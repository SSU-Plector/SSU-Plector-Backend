package ssuPlector.service.developer;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import ssuPlector.aws.s3.AmazonS3Manager;
import ssuPlector.converter.DeveloperConverter;
import ssuPlector.converter.ImageConverter;
import ssuPlector.domain.Developer;
import ssuPlector.domain.Image;
import ssuPlector.domain.Uuid;
import ssuPlector.dto.request.DeveloperDTO;
import ssuPlector.dto.request.DeveloperDTO.DeveloperRequestDTO;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.redis.service.DeveloperHitsService;
import ssuPlector.repository.UuidRepository;
import ssuPlector.repository.developer.DeveloperRepository;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository developerRepository;
    private final DeveloperHitsService developerHitsService;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;

    @Override
    @Transactional
    public Long createDeveloper(DeveloperRequestDTO requestDTO, MultipartFile image) {

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());

        if (developerRepository.findByKakaoId(requestDTO.getKakaoId()) != null)
            throw new GlobalException(GlobalErrorCode.DEVELOPER_DUPLICATE);

        if (developerRepository.findByEmail(requestDTO.getEmail()).isPresent())
            throw new GlobalException(GlobalErrorCode.DEVELOPER_DUPLICATE);

        Developer newDeveloper = DeveloperConverter.toDeveloper(requestDTO);

        String developerImageUrl =
                s3Manager.uploadFile(s3Manager.generateDeveloperKeyName(savedUuid), image);
        Image developerImage = ImageConverter.toImage(developerImageUrl);
        newDeveloper.addImage(developerImage);
        developerRepository.save(newDeveloper);

        return newDeveloper.getId();
    }

    //    @Transactional
    //    public List<Image> createImageList(List<ImageDTO.ImageRequestDTO> requestDTOList) {
    //        return
    // requestDTOList.stream().map(ImageConverter::toImage).collect(Collectors.toList());
    //    }

    @Override
    public Developer getDeveloper(Long id) {
        Developer developer =
                developerRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new GlobalException(GlobalErrorCode.DEVELOPER_NOT_FOUND));
        developerHitsService.incrementHits(id);
        return developer;
    }

    @Override
    public boolean existsByDeveloperId(Long id) {
        return developerRepository.existsById(id);
    }

    @Override
    @Transactional
    public void updateDeveloperHits(Long developerId, Long hit) {
        developerRepository.updateHitsById(developerId, hit);
    }

    @Override
    public List<Long> getUpdateTargetDeveloperIds(List<Long> developerIdList) {
        return developerRepository.findAllByIdIn(developerIdList).stream()
                .map(Developer::getId)
                .toList();
    }

    @Override
    public Page<Developer> getDeveloperList(
            DeveloperDTO.DeveloperListRequestDTO requestDTO, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return developerRepository.findDevelopers(
                requestDTO.getSortType(), requestDTO.getPart(), pageable);
    }
}
