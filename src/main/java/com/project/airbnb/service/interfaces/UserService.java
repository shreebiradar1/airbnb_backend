package com.project.airbnb.service.interfaces;

import com.project.airbnb.dto.request.UserRequest;
import com.project.airbnb.dto.response.UserResponse;
import com.project.airbnb.dto.response.UserStatusRes;
import com.project.airbnb.enums.UserStatus;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  // Create | Add
  UserResponse addUser(UserRequest user);

  // Read
  UserResponse getByUsername(String username);

  UserResponse getByEmail(String email);

  UserResponse getByUsernameOrEmail(String credentials);

  // Update
  UserResponse patchUser(UserRequest request);

  //  UserResponse updateUser(UserUpdateRequest user);

  // Delete
  UserResponse deleteUserPermanently(Long userId);

  UserResponse softDeleteUser(Long userId);

  UserResponse recoverUser(Long userId);

  // This method only use for processing not to the user
  //  UserEntity getByUserId(Long userId);

  // Others | extra methods then CRUD
  UserStatusRes updateUserStatus(Long userId, UserStatus userStatus);

  //  List<BookingResponse> getUserBookingHistory(Long userId);
  void changePassword(Long userId, String oldPassword, String newPassword);
}
