package com.project.airbnb.dto.response;

import com.project.airbnb.entities.helper.HotelDetails;

public record HotelResponse(
    Long hotelId, String hotelName, HotelDetails hotelDetails, Boolean active
    //                            TenantSummary tenantSummary,
    ) {}
