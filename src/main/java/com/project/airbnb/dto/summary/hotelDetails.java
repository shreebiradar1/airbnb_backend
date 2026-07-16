package com.project.airbnb.dto.summary;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record hotelDetails(
    @NotBlank String state,
    @NotBlank String city,
    @NotBlank String address,
    @NotBlank @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number") String hotelPhone,
    @NotBlank @Email String hotelEmail) {}
