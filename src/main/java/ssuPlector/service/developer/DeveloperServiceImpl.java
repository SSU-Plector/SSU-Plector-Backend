package ssuPlector.service.developer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.domain.Developer;
import ssuPlector.dto.request.DeveloperDTO.DeveloperListRequestDTO;
import ssuPlector.dto.request.DeveloperDTO.DeveloperRequestDTO;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.redis.service.DeveloperHitsService;
import ssuPlector.redis.service.RefreshTokenService;
import ssuPlector.repository.developer.DeveloperRepository;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository developerRepository;
    private final DeveloperHitsService developerHitsService;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public Long createDeveloper(String email, DeveloperRequestDTO requestDTO) {
        Developer startDeveloper =
                developerRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new GlobalException(GlobalErrorCode.DEVELOPER_NOT_FOUND));

        startDeveloper.setStartDeveloper(requestDTO);

        return startDeveloper.getId();
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
    public void withdrawDeveloper(Long id) {
        Developer developer = developerRepository.findById(id).get();
        developer.softDelete();
        refreshTokenService.deleteToken(id);
        developerRepository.save(developer);
    }
}
