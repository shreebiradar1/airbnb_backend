package com.project.airbnb.dto.request;

import java.time.LocalDate;

public record HotelSearchReq(
    String city,
    String state,
    String hotelName,
    RoomSearchReq roomDetails,
    LocalDate startDate,
    LocalDate endDate) {}
