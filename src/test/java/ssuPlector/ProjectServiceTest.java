package ssuPlector;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ssuPlector.domain.Project;
import ssuPlector.domain.category.Category;
import ssuPlector.dto.requestDto.ProjectListRequestDto;
import ssuPlector.dto.responseDto.ProjectListResponseDto;
import ssuPlector.repository.project.ProjectRepository;
import ssuPlector.service.project.ProjectService;

@SpringBootTest
@Transactional
public class ProjectServiceTest {

    @Autowired private ProjectService projectService;
    @Autowired private ProjectRepository projectRepository;

    @BeforeEach
    public void setup() {
        // 테스트 데이터 생성
        List<Project> projects = new ArrayList<>();
        projects.add(Project.builder().name("테스트 1").category(Category.AI).build());
        projects.add(Project.builder().name("테스트 2").category(Category.SERVICE).build());
        projects.add(Project.builder().name("테스트 3").category(Category.SERVICE).build());
        projectRepository.saveAll(projects);
    }

    @Test
    public void getProjectListTest() {
        // 테스트 데이터 생성
        ProjectListRequestDto requestDto =
                new ProjectListRequestDto("테스트", Category.SERVICE.name(), "recent"); // 조건 수정 가능

        // 메소드 호출
        ProjectListResponseDto result = projectService.getProjectList(requestDto, 0);

        // 결과 검증
        assertThat(result).isNotNull();
        assertThat(result.getProjectResponseDtoList()).isNotEmpty();

        //        for (ProjectResponseDto project : result.getProjectResponseDtoList()) {
        //            System.out.println("======ProjectResponseDto(테스트,SERVICE,recent)======");
        //            System.out.println("id: " + project.getId());
        //            System.out.println("name: " + project.getName());
        //            System.out.println("category: " + project.getCategory());
        //            System.out.println("hits: " + project.getHits());
        //        }
    }
}
