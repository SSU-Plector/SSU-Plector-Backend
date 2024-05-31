package ssuPlector.service.developer;

import java.util.ArrayList;
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
import ssuPlector.domain.category.DevLanguage;
import ssuPlector.domain.category.DevTools;
import ssuPlector.domain.category.TechStack;
import ssuPlector.dto.request.DeveloperDTO;
import ssuPlector.dto.request.DeveloperDTO.DeveloperListRequestDTO;
import ssuPlector.dto.request.DeveloperDTO.DeveloperRequestDTO;
import ssuPlector.dto.request.DeveloperDTO.DeveloperUpdateRequestDTO;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.redis.service.DeveloperHitsService;
import ssuPlector.repository.UuidRepository;
import ssuPlector.repository.developer.DeveloperRepository;
import ssuPlector.service.BaseMethod;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository developerRepository;
    private final DeveloperHitsService developerHitsService;
    private final BaseMethod baseMethod;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;

    @Override
    @Transactional
    public Long createDeveloper(String email, DeveloperRequestDTO requestDTO) {
        Developer startDeveloper =
                developerRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new GlobalException(GlobalErrorCode.DEVELOPER_NOT_FOUND));
        ArrayList<DevLanguage> newLanguage = baseMethod.fillList(requestDTO.getLanguageList());
        ArrayList<DevTools> newDevTool = baseMethod.fillList(requestDTO.getDevToolList());
        ArrayList<TechStack> newTechStack = baseMethod.fillList(requestDTO.getTechStackList());

        startDeveloper.setStartDeveloper(requestDTO, newLanguage, newDevTool, newTechStack);

        return startDeveloper.getId();
    }

    @Override
    @Transactional
    public Long updateDeveloper(Long id, DeveloperUpdateRequestDTO requestDTO) {
        Developer developer =
                developerRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new GlobalException(GlobalErrorCode.DEVELOPER_NOT_FOUND));
        ArrayList<DevLanguage> newLanguage = baseMethod.fillList(requestDTO.getLanguageList());
        ArrayList<DevTools> newDevTool = baseMethod.fillList(requestDTO.getDevToolList());
        ArrayList<TechStack> newTechStack = baseMethod.fillList(requestDTO.getTechStackList());

        developer.updateDeveloper(requestDTO, newLanguage, newDevTool, newTechStack);

        return developer.getId();
    }

    @Override
    public Developer getDeveloper(Long id, boolean isHit) {
        Developer developer =
                developerRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new GlobalException(GlobalErrorCode.DEVELOPER_NOT_FOUND));
        if (isHit) developerHitsService.incrementHits(id);
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
    public Page<Developer> getDeveloperList(DeveloperListRequestDTO requestDTO, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return developerRepository.findDevelopers(
                requestDTO.getSortType(), requestDTO.getPart(), pageable);
    }

    @Override
    @Transactional
    public Long createDummyDeveloper(
            DeveloperDTO.DummyDeveloperRequestDTO requestDTO, MultipartFile image) {
        if (developerRepository.findByEmail(requestDTO.getEmail()).isPresent())
            throw new GlobalException(GlobalErrorCode.DEVELOPER_DUPLICATE);

        Developer dummyDeveloper = DeveloperConverter.toDeveloper(requestDTO);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());

        String developerImageUrl =
                s3Manager.uploadFile(s3Manager.generateProjectKeyName(savedUuid), image);
        Image developerImage = ImageConverter.toImage(developerImageUrl);
        dummyDeveloper.addImage(developerImage);

        return developerRepository.save(dummyDeveloper).getId();
    }
}
