package com.project.airbnb.service.interfaces;

import com.project.airbnb.dto.request.RoomRequest;
import com.project.airbnb.dto.response.RoomResponse;
import com.project.airbnb.enums.RoomType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RoomService {

  RoomResponse createRoom(RoomRequest request);

  RoomResponse getRoomById(Long id);

  List<RoomResponse> getRoomByHotelId(Long hotelId);

  List<RoomResponse> getAllRooms();

  List<RoomResponse> getAvailableRooms(Long id, LocalDate checkIn, LocalDate checkOut);

  List<RoomResponse> getRoomByType(Long hotelId, RoomType roomType);

  RoomResponse updateRoom(RoomRequest request);

  void deleteRoom(Long id);

  RoomResponse updateRoomPrice(Long roomId, BigDecimal price);
}
