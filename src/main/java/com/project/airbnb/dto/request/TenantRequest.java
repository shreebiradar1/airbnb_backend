package com.project.airbnb.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TenantRequest(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank @Email String email,
    @NotBlank @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number") String mobile,
    @NotBlank @Size(min = 6) String password) {}
