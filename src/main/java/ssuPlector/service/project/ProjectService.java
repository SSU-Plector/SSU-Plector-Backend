package ssuPlector.service.project;

import ssuPlector.dto.requestDto.ProjectListRequestDto;
import ssuPlector.dto.responseDto.ProjectListResponseDto;

public interface ProjectService {
    /** 함수 정의 필요 */
    ProjectListResponseDto getProjectList(ProjectListRequestDto requestDto, int page);
}
