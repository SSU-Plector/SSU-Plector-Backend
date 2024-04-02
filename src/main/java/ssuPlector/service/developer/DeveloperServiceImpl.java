package ssuPlector.service.developer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.converter.DeveloperConverter;
import ssuPlector.converter.ImageConverter;
import ssuPlector.domain.Developer;
import ssuPlector.domain.Image;
import ssuPlector.dto.request.DeveloperDTO.DeveloperRequestDTO;
import ssuPlector.dto.request.ImageDTO;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.redis.service.DeveloperHitsService;
import ssuPlector.repository.developer.DeveloperRepository;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository developerRepository;
    private final DeveloperHitsService developerHitsService;

    @Override
    @Transactional
    public Long createDeveloper(DeveloperRequestDTO requestDTO) {

        if (developerRepository.findByKakaoId(requestDTO.getKakaoId()) != null)
            throw new GlobalException(GlobalErrorCode.DEVELOPER_DUPLICATE);

        if (developerRepository.findByEmail(requestDTO.getEmail()).isPresent())
            throw new GlobalException(GlobalErrorCode.DEVELOPER_DUPLICATE);

        Developer newDeveloper = DeveloperConverter.toDeveloper(requestDTO);

        List<Image> imageList = createImageList(requestDTO.getImageList());
        if (imageList.size() == 1) { // 이미지가 1개인 경우 mainImage 설정
            imageList.get(0).setMainImage();
        }
        imageList.forEach(newDeveloper::addImage);
        developerRepository.save(newDeveloper);

        return newDeveloper.getId();
    }

    @Transactional
    public List<Image> createImageList(List<ImageDTO.ImageRequestDTO> requestDTOList) {
        return requestDTOList.stream().map(ImageConverter::toImage).collect(Collectors.toList());
    }

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
}
