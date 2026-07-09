package com.project.airbnb.mapper;

import com.project.airbnb.dto.request.GuestRequest;
import com.project.airbnb.dto.request.GuestUpdateReq;
import com.project.airbnb.dto.response.GuestResponse;
import com.project.airbnb.entities.GuestEntity;

public class GuestMapper {

  public static GuestEntity toEntity(GuestRequest request) {
    if (request == null) throw new NullPointerException("GuestRequest cannot be null");
    GuestEntity guest = new GuestEntity();
    guest.setName(request.name());
    guest.setAge(request.age());
    guest.setGender(request.gender());
    return guest;
  }

  public static GuestEntity toEntity(GuestUpdateReq request) {
    if (request == null) throw new NullPointerException("GuestRequest cannot be null");
    GuestEntity guest = new GuestEntity();
    if (request.guestId() != null) {
      if (request.name() != null) guest.setName(request.name());
      if (request.age() != null) guest.setAge(request.age());
      if (request.gender() != null) guest.setGender(request.gender());
    } else throw new NullPointerException("GuestId cannot be null");

    return guest;
  }

  public static GuestResponse toResponse(GuestEntity guest) {
    return new GuestResponse(
        guest.getGuestId(), guest.getName(), guest.getAge(), guest.getGender());
  }
}
