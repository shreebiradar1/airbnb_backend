package com.project.airbnb.dto.response;

import com.project.airbnb.enums.RoomType;
import java.math.BigDecimal;
import java.util.List;

public record HotelSearchRes(
    String name, String city, String address, List<RoomType> roomType, BigDecimal price) {}
