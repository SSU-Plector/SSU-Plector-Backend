package ssuPlector.repository.developer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ssuPlector.domain.Developer;

public interface DeveloperRepositoryCustom {

    Page<Developer> findDevelopers(String sortType, Pageable pageable);
}
