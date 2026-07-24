package com.project.airbnb.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TenantUpdateReq(
    Long tenantId,
    String firstName,
    String lastName,
    @Email String email,
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number") String mobile,
    @Size(min = 6) String password) {}
