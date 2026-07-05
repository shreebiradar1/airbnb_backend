package com.project.airbnb.repository;

import com.project.airbnb.entities.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  public Optional<UserEntity> findByUsername(String username);

  public Optional<UserEntity> findByEmail(String email);

  public Optional<UserEntity> findByUsernameAndPassword(String username, String password);

  public Optional<UserEntity> findByEmailAndPassword(String email, String password);

  public Optional<UserEntity> findByUsernameOrEmail(String username, String email);

  public boolean existsByEmail(String email);

  public boolean existsByUsername(String username);
}
