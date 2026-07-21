package com.project.airbnb.service.implement;

import com.project.airbnb.dto.request.HotelAddRequest;
import com.project.airbnb.dto.request.HotelSearchReq;
import com.project.airbnb.dto.request.RoomSearchReq;
import com.project.airbnb.dto.response.HotelResponse;
import com.project.airbnb.dto.response.HotelSearchRes;
import com.project.airbnb.entities.HotelEntity;
import com.project.airbnb.entities.RoomEntity;
import com.project.airbnb.entities.helper.HotelDetails;
import com.project.airbnb.enums.RoomStatus;
import com.project.airbnb.mapper.HotelMapper;
import com.project.airbnb.repository.HotelRepository;
import com.project.airbnb.service.interfaces.HotelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

  private final HotelRepository hotelRepo;

  @Override
  @Transactional
  public HotelResponse addHotel(HotelAddRequest hotelRequest) {
    HotelEntity hotel = HotelMapper.toEntity(hotelRequest);
    return toResponse(hotelRepo.save(hotel));
  }

  @Override
  @Transactional(readOnly = true)
  public HotelResponse getHotelById(Long id) {
    return toResponse(getHotelOrThrow(id));
  }

  @Override
  @Transactional(readOnly = true)
  public List<HotelSearchRes> getHotelDetails(HotelSearchReq request) {
    return hotelRepo.findAll().stream()
        .filter(hotel -> Boolean.TRUE.equals(hotel.getActive()))
        .filter(hotel -> matchesHotelSearch(hotel, request))
        .map(HotelMapper::toSearchResponse)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<HotelResponse> getActiveHotel() {
    return hotelRepo.findAll().stream()
        .filter(hotel -> Boolean.TRUE.equals(hotel.getActive()))
        .map(this::toResponse)
        .toList();
  }

  @Override
  @Transactional
  public HotelResponse updateHotel(HotelAddRequest hotelRequest) {
    if (hotelRequest.hotelId() == null) {
      throw new IllegalArgumentException("Hotel id must not be null.");
    }
    HotelEntity hotel = getHotelOrThrow(hotelRequest.hotelId());
    HotelMapper.patchEntity(hotel, hotelRequest);
    return toResponse(hotel);
  }

  @Override
  @Transactional
  public void deleteHotelById(Long hotelId) {
    HotelEntity hotel = getHotelOrThrow(hotelId);
    hotelRepo.delete(hotel);
  }

  @Override
  @Transactional
  public void activateHotelById(Long hotelId) {
    HotelEntity hotel = getHotelOrThrow(hotelId);
    hotel.setActive(true);
  }

  @Override
  @Transactional
  public void deactivateHotelById(Long hotelId) {
    HotelEntity hotel = getHotelOrThrow(hotelId);
    hotel.setActive(false);
  }

  @Override
  @Transactional(readOnly = true)
  public Long getTotalRooms(Long hotelId) {
    HotelEntity hotel = getHotelOrThrow(hotelId);
    return hotel.getRooms().stream()
        .map(RoomEntity::getTotalCount)
        .filter(count -> count != null)
        .reduce(0L, Long::sum);
  }

  @Override
  @Transactional(readOnly = true)
  public Long getAvailableRoomsCount(Long hotelId) {
    HotelEntity hotel = getHotelOrThrow(hotelId);
    return hotel.getRooms().stream()
        .filter(room -> room.getRoomStatus() == RoomStatus.AVAILABLE)
        .map(RoomEntity::getTotalCount)
        .filter(count -> count != null)
        .reduce(0L, Long::sum);
  }

  private HotelResponse toResponse(HotelEntity hotel) {
    return HotelMapper.toResponse(hotel);
  }

  private HotelEntity getHotelOrThrow(Long hotelId) {
    return hotelRepo.findById(hotelId).orElseThrow(() -> new RuntimeException("Hotel not found"));
  }

  private boolean matchesHotelSearch(HotelEntity hotel, HotelSearchReq request) {
    if (request == null) {
      return true;
    }
    HotelDetails details = hotel.getDetails();
    return matchesText(hotel.getHotelName(), request.hotelName())
        && matchesText(details == null ? null : details.getCity(), request.city())
        && matchesText(details == null ? null : details.getState(), request.state())
        && matchesRoomSearch(hotel, request.roomDetails());
  }

  private boolean matchesRoomSearch(HotelEntity hotel, RoomSearchReq roomSearch) {
    if (roomSearch == null) {
      return true;
    }
    return hotel.getRooms().stream()
        .anyMatch(
            room ->
                (roomSearch.roomType() == null || room.getRoomType() == roomSearch.roomType())
                    && (roomSearch.capacity() == null
                        || (room.getCapacity() != null
                            && room.getCapacity() >= roomSearch.capacity())));
  }

  private boolean matchesText(String actual, String expected) {
    if (expected == null || expected.isBlank()) {
      return true;
    }
    return actual != null && actual.toLowerCase().contains(expected.toLowerCase());
  }
}
