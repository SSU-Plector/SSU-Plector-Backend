package ssuPlector.service.project;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ssuPlector.dto.requestDto.ProjectListRequestDto;
import ssuPlector.dto.responseDto.ProjectListResponseDto;
import ssuPlector.repository.project.ProjectRepository;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public ProjectListResponseDto getProjectList(ProjectListRequestDto requestDto, int page) {
        Pageable pageable = PageRequest.of(page, 30);
        return new ProjectListResponseDto(
                projectRepository.findProjects(
                        requestDto.getSearchString(),
                        requestDto.getCategory(),
                        requestDto.getSortType(),
                        pageable));
    }
}
