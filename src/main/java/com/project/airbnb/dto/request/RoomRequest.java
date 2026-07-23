package com.project.airbnb.dto.request;

import com.project.airbnb.enums.RoomStatus;
import com.project.airbnb.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record RoomRequest(
    @NotNull Long hotelId,
    @NotNull RoomType roomType,
    @NotNull @Positive BigDecimal basePrice,
    @NotNull @Positive Long totalCount,
    @NotNull RoomStatus roomStatus,
    @NotNull @Positive Long capacity) {}
