package com.project.airbnb.mapper;

import com.project.airbnb.dto.request.HotelAddRequest;
import com.project.airbnb.dto.response.HotelResponse;
import com.project.airbnb.dto.response.HotelSearchRes;
import com.project.airbnb.entities.HotelEntity;
import com.project.airbnb.entities.RoomEntity;
import com.project.airbnb.entities.helper.HotelDetails;
import com.project.airbnb.enums.RoomType;
import java.math.BigDecimal;
import java.util.List;

public class HotelMapper {

  public static HotelEntity toEntity(HotelAddRequest request) {
    HotelEntity hotel = new HotelEntity();
    hotel.setHotelName(request.hotelName());
    hotel.setDetails(request.details());
    if (request.active() != null) {
      hotel.setActive(request.active());
    }
    return hotel;
  }

  public static HotelResponse toResponse(HotelEntity hotel) {
    return new HotelResponse(
        hotel.getHotelId(), hotel.getHotelName(), hotel.getDetails(), hotel.getActive());
  }

  public static HotelSearchRes toSearchResponse(HotelEntity hotel) {
    HotelDetails details = hotel.getDetails();
    List<RoomEntity> rooms = hotel.getRooms() == null ? List.of() : hotel.getRooms();

    List<RoomType> roomTypes = rooms.stream().map(RoomEntity::getRoomType).distinct().toList();

    BigDecimal lowestPrice =
        rooms.stream()
            .map(RoomEntity::getBasePrice)
            .filter(price -> price != null)
            .min(BigDecimal::compareTo)
            .orElse(null);

    return new HotelSearchRes(
        hotel.getHotelName(),
        details == null ? null : details.getCity(),
        details == null ? null : details.getHotelAddress(),
        roomTypes,
        lowestPrice);
  }

  public static void patchEntity(HotelEntity hotel, HotelAddRequest request) {
    if (request.hotelName() != null) {
      hotel.setHotelName(request.hotelName());
    }
    if (request.details() != null) {
      hotel.setDetails(request.details());
    }
    if (request.active() != null) {
      hotel.setActive(request.active());
    }
  }
}
