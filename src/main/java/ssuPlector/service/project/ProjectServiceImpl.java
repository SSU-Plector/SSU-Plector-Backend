package ssuPlector.service.project;

import static ssuPlector.dto.request.ImageDTO.*;
import static ssuPlector.dto.request.ProjectDTO.*;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.converter.ImageConverter;
import ssuPlector.converter.ProjectConverter;
import ssuPlector.domain.Image;
import ssuPlector.domain.Project;
import ssuPlector.domain.ProjectUser;
import ssuPlector.domain.User;
import ssuPlector.domain.category.Category;
import ssuPlector.dto.request.ProjectDTO.ProjectListRequestDto;
import ssuPlector.dto.request.ProjectDTO.ProjectUserDetailRequestDTO;
import ssuPlector.dto.response.ProjectDTO.ProjectListResponseDto;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.redis.service.ProjectHitsService;
import ssuPlector.repository.project.ProjectRepository;
import ssuPlector.repository.projectUser.ProjectUserRepository;
import ssuPlector.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectUserRepository projectUserRepository;
    private final UserRepository userRepository;
    private final ProjectHitsService projectHitsService;

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
        Pageable pageable = PageRequest.of(page, 30);
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
    public Long createProject(ProjectDetailRequestDTO requestDTO) {

        Project newProject = ProjectConverter.toProject(requestDTO);

        List<ProjectUser> projectUserList = createProjectUserList(requestDTO.getProjectUserList());
        projectUserList.forEach(newProject::addProjectUser);

        List<Image> imageList = createImageList(requestDTO.getImageList());
        imageList.forEach(newProject::addImage);

        projectRepository.save(newProject);
        projectUserRepository.saveAll(projectUserList);

        return newProject.getId();
    }

    @Override
    public List<ProjectUser> createProjectUserList(
            List<ProjectUserDetailRequestDTO> requestDTOList) {
        return requestDTOList.stream().map(this::createProjectUser).collect(Collectors.toList());
    }

    public List<Image> createImageList(List<ImageDetailRequestDTO> requestDTOList) {
        return requestDTOList.stream().map(ImageConverter::toImage).collect(Collectors.toList());
    }

    @Override
    public ProjectUser createProjectUser(ProjectUserDetailRequestDTO requestDTO) {

        User user = userRepository.findByEmail(requestDTO.getEmail()).orElse(null);

        ProjectUser newProjectUser =
                ProjectUser.builder()
                        .name(requestDTO.getName())
                        .partList(requestDTO.getPartList())
                        .isTeamLeader(requestDTO.getIsTeamLeader())
                        .build();

        if (user != null) { // 계정이 있는 프로젝트 부원인 경우
            user.addProjectUser(newProjectUser);
        }
        return newProjectUser;
    }
}
