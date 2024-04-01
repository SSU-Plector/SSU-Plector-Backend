package ssuPlector.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ssuPlector.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("UPDATE User u SET u.hits = :hits WHERE u.id = :id")
    void updateHitsById(@Param("id") Long id, @Param("hits") Long hits);

    List<User> findAllByIdIn(List<Long> id);

    Optional<User> findByEmail(String email);

    User findByKakaoId(String kakaoId);
}
