package com.project.airbnb.dto.request;

import com.project.airbnb.enums.Gender;
import com.project.airbnb.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UserRequest(
    @NotBlank String username,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @Email String email,
    @NotBlank @Size(min = 6) String password,
    @NotBlank @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number") String mobile,
    @NotNull Gender gender,
    @Past LocalDate birthDate,
    Role role) {}
