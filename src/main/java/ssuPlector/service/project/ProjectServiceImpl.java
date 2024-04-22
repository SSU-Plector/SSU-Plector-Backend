package ssuPlector.service.project;

import static ssuPlector.dto.request.ProjectDTO.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import ssuPlector.aws.s3.AmazonS3Manager;
import ssuPlector.converter.ImageConverter;
import ssuPlector.converter.ProjectConverter;
import ssuPlector.domain.Developer;
import ssuPlector.domain.Image;
import ssuPlector.domain.Project;
import ssuPlector.domain.ProjectDeveloper;
import ssuPlector.domain.Uuid;
import ssuPlector.domain.category.Category;
import ssuPlector.dto.request.ProjectDTO.ProjectListRequestDto;
import ssuPlector.dto.response.ProjectDTO.ProjectListResponseDto;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.redis.service.ProjectHitsService;
import ssuPlector.repository.UuidRepository;
import ssuPlector.repository.developer.DeveloperRepository;
import ssuPlector.repository.project.ProjectRepository;
import ssuPlector.repository.projectDevloper.ProjectDeveloperRepository;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectDeveloperRepository projectDeveloperRepository;
    private final DeveloperRepository developerRepository;
    private final ProjectHitsService projectHitsService;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;

    @Override
    public Project getProject(Long projectId) {
        Project project =
                projectRepository
                        .findById(projectId)
                        .orElseThrow(() -> new GlobalException(GlobalErrorCode.PROJECT_NOT_FOUND));
        projectHitsService.incrementHits(projectId);
        return project;
    }

    @Override
    @Transactional
    public void updateProjectHits(Long projectId, Long hit) {
        projectRepository.updateHitsById(projectId, hit);
    }

    @Override
    public List<Long> getUpdateTargetProjectIds(List<Long> projectIdList) {
        return projectRepository.findAllByIdIn(projectIdList).stream().map(Project::getId).toList();
    }

    @Override
    public boolean existsByProjectId(Long id) {
        return projectRepository.existsById(id);
    }

    @Override
    public ProjectListResponseDto getProjectList(ProjectListRequestDto requestDto, int page) {
        Pageable pageable = PageRequest.of(page, 4);
        String category = requestDto.getCategory();
        if (category != null && !EnumUtils.isValidEnum(Category.class, category))
            throw new GlobalException(GlobalErrorCode.CATEGORY_NOT_FOUND);
        return new ProjectListResponseDto(
                projectRepository.findProjects(
                        requestDto.getSearchString(),
                        requestDto.getCategory(),
                        requestDto.getSortType(),
                        pageable));
    }

    @Override
    @Transactional
    public Long createProject(ProjectRequestDTO requestDTO, MultipartFile image) {

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());

        Project newProject = ProjectConverter.toProject(requestDTO);

        List<ProjectDeveloper> projectDeveloperList =
                createProjectDeveloperList(requestDTO.getProjectDevloperList());
        projectDeveloperList.forEach(newProject::addProjectDeveloper);

        String projectImageUrl =
                s3Manager.uploadFile(s3Manager.generateProjectKeyName(savedUuid), image);
        Image projectImage = ImageConverter.toImage(projectImageUrl);
        newProject.addImage(projectImage);

        projectRepository.save(newProject);
        projectDeveloperRepository.saveAll(projectDeveloperList);

        return newProject.getId();
    }

    @Transactional
    public List<ProjectDeveloper> createProjectDeveloperList(
            List<ProjectDeveloperRequestDTO> requestDTOList) {
        return requestDTOList.stream()
                .map(this::createProjectDeveloper)
                .collect(Collectors.toList());
    }

    //    @Transactional
    //    public List<Image> createImageList(List<ImageRequestDTO> requestDTOList) {
    //        return
    // requestDTOList.stream().map(ImageConverter::toImage).collect(Collectors.toList());
    //    }

    @Transactional
    public ProjectDeveloper createProjectDeveloper(ProjectDeveloperRequestDTO requestDTO) {

        Developer developer = developerRepository.findByEmail(requestDTO.getEmail()).orElse(null);

        ProjectDeveloper newProjectDeveloper =
                ProjectDeveloper.builder()
                        .name(requestDTO.getName())
                        .partList(requestDTO.getPartList())
                        .isTeamLeader(requestDTO.getIsTeamLeader())
                        .build();

        if (developer != null) { // 계정이 있는 프로젝트 부원인 경우
            developer.addProjectDeveloper(newProjectDeveloper);
        }
        return newProjectDeveloper;
    }
}
