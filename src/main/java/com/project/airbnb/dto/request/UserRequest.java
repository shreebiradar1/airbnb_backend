package com.project.airbnb.dto.request;

import com.project.airbnb.dto.summary.UserSummary;
import com.project.airbnb.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank String username,
    @NotBlank @Size(min = 6) String password,
    UserSummary summary,
    Role role) {}
