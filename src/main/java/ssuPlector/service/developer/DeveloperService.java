package ssuPlector.service.developer;

import ssuPlector.domain.User;

public interface DeveloperService {
    User getDeveloper(Long id);

    boolean existsByDeveloperId(Long id);
}
