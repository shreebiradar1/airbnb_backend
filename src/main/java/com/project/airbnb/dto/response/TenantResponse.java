package com.project.airbnb.dto.response;

import com.project.airbnb.dto.summary.HotelSummary;
import java.util.List;

public record TenantResponse(
    Long tenantId, String name, String email, String mobile, List<HotelSummary> hotelSummary) {}
