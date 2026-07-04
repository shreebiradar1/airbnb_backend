package com.project.airbnb.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.project.airbnb.entities.UserEntity;

@Repository
public interface UserRepository {
  public Optional<UserEntity> findByUsername(String username);

  public Optional<UserEntity> findByEmail(String email);

  public Optional<UserEntity> findByUsernameAndPassword(String username, String password);

  public Optional<UserEntity> findByEmailAndPassword(String email, String password);

  public Optional<UserEntity> findByUsernameOrEmail(String username, String email);

  public boolean existsByEmail(String email);

  public boolean existsByUsername(String username);
}
