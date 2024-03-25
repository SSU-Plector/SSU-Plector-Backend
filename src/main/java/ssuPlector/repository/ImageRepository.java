package ssuPlector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssuPlector.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
