package com.project.airbnb.dto.response;

import com.project.airbnb.dto.summary.RoomSummary;
import java.math.BigDecimal;

public record RoomResponse(
    // Hotel Summary
    RoomSummary roomSummary, BigDecimal basePrice) {}
