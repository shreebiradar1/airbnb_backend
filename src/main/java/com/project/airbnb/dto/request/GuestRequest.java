package com.project.airbnb.dto.request;

import com.project.airbnb.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GuestRequest(
    @NotBlank String name, @NotNull @Positive Long age, @NotNull Gender gender) {}
