package ssuPlector.service.developer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.converter.DeveloperConverter;
import ssuPlector.converter.ImageConverter;
import ssuPlector.domain.Developer;
import ssuPlector.domain.Image;
import ssuPlector.dto.request.DeveloperDTO.DeveloperRequestDTO;
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

        Image image = ImageConverter.toImage(requestDTO.getImageLink());

        newDeveloper.addImage(image);
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
}
