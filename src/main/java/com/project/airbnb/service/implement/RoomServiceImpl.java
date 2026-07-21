package com.project.airbnb.service.implement;

import com.project.airbnb.dto.request.RoomRequest;
import com.project.airbnb.dto.response.RoomResponse;
import com.project.airbnb.entities.HotelEntity;
import com.project.airbnb.entities.RoomEntity;
import com.project.airbnb.enums.RoomStatus;
import com.project.airbnb.enums.RoomType;
import com.project.airbnb.mapper.RoomMapper;
import com.project.airbnb.repository.HotelRepository;
import com.project.airbnb.repository.RoomRepository;
import com.project.airbnb.service.interfaces.RoomService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
  private final RoomRepository roomRepo;
  private final HotelRepository hotelRepo;

  @Override
  @Transactional
  public RoomResponse createRoom(RoomRequest request) {
    HotelEntity hotel = getHotelOrThrow(request.hotelId());
    RoomEntity room = RoomMapper.toEntity(request);
    room.setHotel(hotel);
    return toResponse(roomRepo.save(room));
  }

  @Override
  public RoomResponse getRoomById(Long id) {
    return toResponse(getRoomOrThrow(id));
  }

  @Override
  public List<RoomResponse> getRoomByHotelId(Long hotelId) {
    ensureHotelExists(hotelId);
    return toResponseList(roomRepo.findByHotelHotelId(hotelId));
  }

  @Override
  public List<RoomResponse> getAllRooms() {
    return toResponseList(roomRepo.findAll());
  }

  @Override
  public List<RoomResponse> getAvailableRooms(Long id, LocalDate checkIn, LocalDate checkOut) {
    if (checkIn == null || checkOut == null) {
      throw new IllegalArgumentException("Check-in and check-out dates must not be null.");
    }
    if (!checkIn.isBefore(checkOut)) {
      throw new IllegalArgumentException("Check-in date must be before check-out date.");
    }
    ensureHotelExists(id);
    return toResponseList(roomRepo.findByHotelHotelIdAndRoomStatus(id, RoomStatus.AVAILABLE));
  }

  @Override
  public List<RoomResponse> getRoomByType(Long hotelId, RoomType roomType) {
    if (roomType == null) {
      throw new IllegalArgumentException("Room type must not be null.");
    }
    ensureHotelExists(hotelId);
    return toResponseList(roomRepo.findByHotelHotelIdAndRoomType(hotelId, roomType));
  }

  @Override
  @Transactional
  public RoomResponse updateRoom(RoomRequest request) {
    RoomEntity room = getRoomForRequestOrThrow(request);
    room.setRoomType(request.roomType());
    room.setBasePrice(request.basePrice());
    room.setTotalCount(request.totalCount());
    room.setRoomStatus(request.roomStatus());
    room.setCapacity(request.capacity());
    return toResponse(room);
  }

  @Override
  @Transactional
  public void deleteRoom(Long id) {
    RoomEntity room = getRoomOrThrow(id);
    roomRepo.delete(room);
  }

  @Override
  @Transactional
  public RoomResponse updateRoomPrice(Long roomId, BigDecimal price) {
    if (price == null || price.signum() <= 0) {
      throw new IllegalArgumentException("Room price must be positive.");
    }
    RoomEntity room = getRoomOrThrow(roomId);
    room.setBasePrice(price);
    return toResponse(room);
  }

  private RoomResponse toResponse(RoomEntity room) {
    return RoomMapper.toResponse(room);
  }

  private List<RoomResponse> toResponseList(List<RoomEntity> rooms) {
    return rooms.stream().map(this::toResponse).toList();
  }

  private RoomEntity getRoomOrThrow(Long id) {
    return roomRepo.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
  }

  private HotelEntity getHotelOrThrow(Long hotelId) {
    return hotelRepo.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));
  }

  private void ensureHotelExists(Long hotelId) {
    if (!hotelRepo.existsById(hotelId)) {
      throw new RuntimeException("Hotel not found");
    }
  }

  private RoomEntity getRoomForRequestOrThrow(RoomRequest request) {
    List<RoomEntity> rooms =
        roomRepo.findByHotelHotelIdAndRoomTypeAndCapacity(
            request.hotelId(), request.roomType(), request.capacity());
    if (rooms.isEmpty()) {
      throw new RuntimeException("Room not found");
    }
    if (rooms.size() > 1) {
      throw new IllegalStateException("Multiple rooms match the provided request.");
    }
    return rooms.getFirst();
  }
}
