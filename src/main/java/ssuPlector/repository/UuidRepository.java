package ssuPlector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ssuPlector.domain.Uuid;

@Repository
public interface UuidRepository extends JpaRepository<Uuid, Long> {}
