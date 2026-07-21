package com.project.airbnb.mapper;

import com.project.airbnb.dto.request.RoomRequest;
import com.project.airbnb.dto.response.RoomResponse;
import com.project.airbnb.dto.summary.RoomSummary;
import com.project.airbnb.entities.RoomEntity;

public class RoomMapper {

  public static RoomEntity toEntity(RoomRequest request) {
    RoomEntity room = new RoomEntity();
    room.setRoomType(request.roomType());
    room.setBasePrice(request.basePrice());
    room.setRoomStatus(request.roomStatus());
    room.setCapacity(request.capacity());
    room.setTotalCount(request.totalCount());
    //    room.setHotel();
    return room;
  }

  public static RoomResponse toResponse(RoomEntity room) {

    return new RoomResponse(
        new RoomSummary(room.getRoomType(), room.getCapacity()), room.getBasePrice());
  }
}
