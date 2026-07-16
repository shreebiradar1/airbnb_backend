package com.project.airbnb.dto.summary;

import com.project.airbnb.enums.RoomType;

public record RoomSummary(RoomType roomType, Long capacity) {}
