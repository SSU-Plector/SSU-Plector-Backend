package ssuPlector.repository.developer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ssuPlector.domain.Developer;
import ssuPlector.domain.category.SocialType;

@Repository
public interface DeveloperRepository
        extends JpaRepository<Developer, Long>, DeveloperRepositoryCustom {
    @Modifying
    @Query("UPDATE Developer d SET d.hits = :hits WHERE d.id = :id")
    void updateHitsById(@Param("id") Long id, @Param("hits") Long hits);

    List<Developer> findAllByIdIn(List<Long> id);

    Optional<Developer> findByEmail(String email);

    Developer findByKakaoId(String kakaoId);

    @Query(
            value = "select * from developer where email = :email and social_type = :socialType",
            nativeQuery = true)
    Optional<Developer> findByEmailAndSocialTypeWithDeleted(
            @Param("email") String email, @Param("socialType") SocialType socialType);
}
