package com.project.airbnb.mapper;

import com.project.airbnb.dto.request.UserRequest;
import com.project.airbnb.dto.response.UserResponse;
import com.project.airbnb.dto.summary.UserSummary;
import com.project.airbnb.entities.UserEntity;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserMapper {
  private static PasswordEncoder encoder;

  public static UserEntity toEntity(UserRequest request) {
    UserEntity user = new UserEntity();
    user.setUsername(request.username());
    user.setPassword(encoder.encode(request.password()));
    user.setFirstName(request.summary().firstName());
    user.setLastName(request.summary().lastName());
    user.setEmail(request.summary().email());
    user.setMobile(request.summary().mobile());
    user.setBirthDate(request.summary().birthdate());
    user.setGender(request.summary().gender());
    user.setRole(Set.of(request.role()));
    return user;
  }

  public static UserResponse toResponse(UserEntity user) {
    UserSummary summary =
        new UserSummary(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getMobile(),
            user.getBirthDate(),
            user.getGender());

    return new UserResponse(
        user.getUserId(), user.getUsername(), summary, user.getRole(), user.getStatus());
  }

  // For Username, password and roles separate method will be implemented
  public static void patchEntity(UserEntity user, UserRequest request) {
    // I thought for username we should have a different method because we have to perform some
    // check/opertion in the DB
    if (request.summary().firstName() != null) {
      user.setFirstName(request.summary().firstName());
    }
    if (request.summary().lastName() != null) {
      user.setLastName(request.summary().lastName());
    }

    // For email and mobile what i was think is i should send a mail or something authentication
    // that the mail or mobile number is changing. Will see in future if i had time i will implement
    // that.
    if (request.summary().email() != null) {
      user.setEmail(request.summary().email());
    }
    if (request.summary().mobile() != null) {
      user.setMobile(request.summary().mobile());
    }
    if (request.summary().birthdate() != null) {
      user.setBirthDate(request.summary().birthdate());
    }
    if (request.summary().gender() != null) {
      user.setGender(request.summary().gender());
    }
  }
}
