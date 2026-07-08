package com.project.airbnb.controller;

import com.project.airbnb.dto.request.UserRequest;
import com.project.airbnb.dto.response.UserResponse;
import com.project.airbnb.dto.response.UserStatusRes;
import com.project.airbnb.enums.UserStatus;
import com.project.airbnb.service.interfaces.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRequest userRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userRequest));
  }

  //  @GetMapping("/{username}")
  //  public ResponseEntity<UserResponse> getByUsername(@NotBlank @PathVariable String username) {
  //    return ResponseEntity.status(HttpStatus.OK).body(userService.getByUsername(username));
  //  }
  //
  //  @GetMapping("/{email}")
  //  public ResponseEntity<UserResponse> getByEmail(@NotBlank @PathVariable String email) {
  //    return ResponseEntity.status(HttpStatus.OK).body(userService.getByEmail(email));
  //  }

  //  @GetMapping("/{identifier}")
  @GetMapping
  public ResponseEntity<UserResponse> getByUsernameOrEmail(
      @NotBlank @RequestParam String identifier) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getByUsernameOrEmail(identifier));
  }

  @PatchMapping
  public ResponseEntity<UserResponse> patchUser(@Valid @RequestBody UserRequest user) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.patchUser(user));
  }

  @PatchMapping("/{userId}/status")
  public ResponseEntity<UserStatusRes> updateUserStatus(
      @PathVariable Long userId, @RequestParam UserStatus userStatus) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(userService.updateUserStatus(userId, userStatus));
  }

  @DeleteMapping("/{userId}/permanent")
  public ResponseEntity<UserResponse> deleteUserPermanently(@PathVariable Long userId) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserPermanently(userId));
  }

  @PatchMapping("/{userId}/deactivate")
  public ResponseEntity<UserResponse> softDeleteUser(@PathVariable Long userId) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.softDeleteUser(userId));
  }

  @PatchMapping("/{userId}/restore")
  public ResponseEntity<UserResponse> recoverUser(@PathVariable Long userId) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.recoverUser(userId));
  }
}
