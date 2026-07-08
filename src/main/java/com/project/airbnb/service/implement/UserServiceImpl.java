package com.project.airbnb.service.implement;

import com.project.airbnb.dto.request.UserRequest;
import com.project.airbnb.dto.response.UserResponse;
import com.project.airbnb.dto.response.UserStatusRes;
import com.project.airbnb.entities.UserEntity;
import com.project.airbnb.enums.UserStatus;
import com.project.airbnb.mapper.UserMapper;
import com.project.airbnb.repository.UserRepository;
import com.project.airbnb.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// For @Transactional annotated method repo.save() is not mentioned because JPA perform dirty
// checking
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository repo;

  //  private final PasswordEncoder encoder;

  @Override
  @Transactional
  public UserResponse addUser(UserRequest request) {
    if (repo.existsByUsername(request.username())) {
      throw new IllegalStateException("Username already exists");
    }
    if (repo.existsByEmail(request.summary().email())) {
      throw new IllegalStateException("Email already exists");
    }
    UserEntity user = UserMapper.toEntity(request);
    //    user.setPassword(encoder.encode(user.getPassword()));
    UserEntity savedUser = repo.save(user);
    return toResponse(savedUser);
  }

  @Override
  public UserResponse getByUsername(final String username) {
    UserEntity user =
        repo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    return toResponse(user);
  }

  @Override
  public UserResponse getByEmail(final String email) {
    UserEntity user =
        repo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User with the specified email not found."));

    return toResponse(user);
  }

  @Override
  public UserResponse getByUsernameOrEmail(final String credentials) {
    UserEntity user =
        repo.findByEmail(credentials)
            .or(() -> repo.findByUsername(credentials))
            .orElseThrow(() -> new RuntimeException("User not found"));
    return toResponse(user);
  }

  //  @Override
  //  public UserEntity getByUserId(Long userId) {
  //    return getUserOrThrow(userId);
  //  }

  @Override
  @Transactional
  public UserResponse patchUser(UserRequest request) {
    UserEntity user =
        repo.findByUsername(request.username())
            .orElseThrow(() -> new RuntimeException("No user exists with this username."));
    UserMapper.patchEntity(user, request);
    repo.save(user);
    return toResponse(user);
  }

  @Override
  @Transactional
  public UserStatusRes updateUserStatus(Long userId, UserStatus newStatus) {
    UserEntity user = getUserOrThrow(userId);
    UserStatus currentStatus = user.getStatus();
    UserStatusRes response =
        new UserStatusRes(user.getUserId(), user.getUsername(), currentStatus, newStatus);
    validateStatusTransition(currentStatus, newStatus);
    user.setStatus(newStatus);
    return response;
  }

  @Override
  @Transactional
  public UserResponse deleteUserPermanently(Long userId) {
    UserEntity user = getUserOrThrow(userId);
    user.getRole().clear();
    repo.save(user);
    repo.delete(user);
    return toResponse(user);
  }

  @Override
  @Transactional
  public UserResponse softDeleteUser(Long userId) {
    UserEntity user = getUserOrThrow(userId);
    user.setStatus(UserStatus.DELETED);
    return toResponse(user);
  }

  @Override
  @Transactional
  public UserResponse recoverUser(Long userId) {
    UserEntity user = getUserOrThrow(userId);
    user.setStatus(UserStatus.INACTIVE);
    return toResponse(user);
  }

  // Will be implemented further as JWT and other authentication tool were install
  @Override
  public void changePassword(Long userId, String oldPassword, String newPassword) {}

  // Helper method
  private UserResponse toResponse(UserEntity user) {
    return UserMapper.toResponse(user);
  }

  private UserEntity getUserOrThrow(Long userId) {
    return repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
  }

  private void validateStatusTransition(UserStatus currentStatus, UserStatus newStatus) {
    if (newStatus == null) throw new IllegalArgumentException("New status must not be null.");

    if (currentStatus == UserStatus.DELETED)
      throw new IllegalStateException(
          "Cannot change the status of a deleted user. Recover the user first.");

    if (currentStatus == UserStatus.BANNED)
      throw new IllegalStateException("Banned user cannot change status");

    if (currentStatus == newStatus)
      throw new IllegalStateException("User is already " + currentStatus.name().toLowerCase());
  }
}
