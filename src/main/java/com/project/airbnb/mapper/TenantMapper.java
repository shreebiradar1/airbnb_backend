package com.project.airbnb.mapper;

import com.project.airbnb.dto.request.TenantRequest;
import com.project.airbnb.dto.response.TenantResponse;
import com.project.airbnb.dto.summary.HotelSummary;
import com.project.airbnb.entities.HotelEntity;
import com.project.airbnb.entities.TenantEntity;
import com.project.airbnb.entities.helper.HotelDetails;
import java.util.ArrayList;
import java.util.List;

public class TenantMapper {
  public static TenantEntity toEntity(TenantRequest request) {
    if (request == null) {
      throw new NullPointerException("TenantRequest cannot be null");
    }

    TenantEntity entity = new TenantEntity();
    entity.setFirstName(request.firstName());
    entity.setLastName(request.lastName());
    entity.setEmail(request.email());
    entity.setMobile(request.mobile());
    entity.setPassword(request.password());
    return entity;
  }

  public static TenantResponse toResponse(TenantEntity entity) {
    if (entity == null) {
      throw new NullPointerException("TenantEntity cannot be null");
    }

    return new TenantResponse(
        entity.getTenantId(),
        entity.getFullName(),
        entity.getEmail(),
        entity.getMobile(),
        toHotelSummary(entity.getHotels()));
  }

  private static List<HotelSummary> toHotelSummary(List<HotelEntity> hotels) {
    if (hotels == null || hotels.isEmpty()) {
      return List.of();
    }
    List<HotelSummary> hotelSummaryList = new ArrayList<>();

    for (HotelEntity hotel : hotels) {
      HotelDetails details = hotel.getDetails();
      HotelSummary summary =
          new HotelSummary(hotel.getHotelId(), hotel.getHotelName(), details.getCity());
      hotelSummaryList.add(summary);
    }
    return hotelSummaryList;
  }
}
