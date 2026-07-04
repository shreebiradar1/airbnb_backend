package com.project.airbnb.dto.summary;

import com.project.airbnb.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public record UserSummary(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @Email String email,
    @NotBlank @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number") String mobile,
    @Past LocalDate birthdate,
    @NotNull Gender gender) {}
