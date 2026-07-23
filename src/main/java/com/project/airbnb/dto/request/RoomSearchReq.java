package com.project.airbnb.dto.request;

import com.project.airbnb.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public record RoomSearchReq(
    RoomType roomType,
    @NotNull(message = "This field can not be empty") LocalDate startDate,
    @NotNull(message = "This field can not be empty") LocalDate endDate,
    @Positive Long capacity) {}
