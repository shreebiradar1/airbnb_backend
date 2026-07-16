package com.project.airbnb.dto.request;

import com.project.airbnb.entities.helper.HotelDetails;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HotelAddRequest(
    Long hotelId,
    @NotBlank String hotelName,
    @NotNull Long tenantId,
    @Valid HotelDetails details,
    Boolean active) {}
