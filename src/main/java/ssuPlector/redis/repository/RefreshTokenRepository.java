package ssuPlector.redis.repository;

import org.springframework.data.repository.CrudRepository;

import ssuPlector.redis.domain.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {}
