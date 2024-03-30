package ssuPlector.service.projectDeveloper;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ssuPlector.domain.ProjectUser;
import ssuPlector.domain.User;
import ssuPlector.repository.projectUser.ProjectUserRepository;
import ssuPlector.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class ProjectDeveloperServiceImpl implements ProjectDeveloperService {
    private final ProjectUserRepository projectUserRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void connectProjectDeveloper(Long projectId) {
        List<ProjectUser> projectUsers =
                projectUserRepository.findByProject_Id(projectId);
        for (ProjectUser projectUser : projectUsers) {
            if (projectUser.getUser() == null) {
                User user = userRepository.findByName(projectUser.getName());
                projectUser.update(user);
            }
        }
    }
}
