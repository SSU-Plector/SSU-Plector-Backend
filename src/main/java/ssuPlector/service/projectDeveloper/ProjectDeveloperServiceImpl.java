package ssuPlector.service.projectDeveloper;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.domain.Developer;
import ssuPlector.domain.ProjectDeveloper;
import ssuPlector.repository.developer.DeveloperRepository;
import ssuPlector.repository.projectDevloper.ProjectDeveloperRepository;

@Service
@RequiredArgsConstructor
public class ProjectDeveloperServiceImpl implements ProjectDeveloperService {
    private final ProjectDeveloperRepository projectDeveloperRepository;
    private final DeveloperRepository developerRepository;

    @Override
    @Transactional
    public void connectProjectDeveloper(Long projectId) {
        List<ProjectDeveloper> projectDevelopers =
                projectDeveloperRepository.findByProject_Id(projectId);
        for (ProjectDeveloper projectDeveloper : projectDevelopers) {
            if (projectDeveloper.getDeveloper() == null) {
                Developer developer =
                        developerRepository.findByKakaoId(projectDeveloper.getKakaoId());
                projectDeveloper.update(developer);
            }
        }
    }
}
