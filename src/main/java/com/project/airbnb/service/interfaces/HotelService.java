package com.project.airbnb.service.interfaces;

import com.project.airbnb.dto.request.HotelAddRequest;
import com.project.airbnb.dto.request.HotelSearchReq;
import com.project.airbnb.dto.response.HotelResponse;
import com.project.airbnb.dto.response.HotelSearchRes;
import java.util.List;

public interface HotelService {
  HotelResponse addHotel(HotelAddRequest hotelRequest);

  HotelResponse getHotelById(Long id); // Status of just hotel

  List<HotelSearchRes> getHotelDetails(HotelSearchReq request); // Status of hotel and rooms

  List<HotelResponse> getActiveHotel();

  HotelResponse updateHotel(HotelAddRequest hotelRequest);

  void deleteHotelById(Long hotelId);

  void activateHotelById(Long hotelId);

  void deactivateHotelById(Long hotelId);

  Long getTotalRooms(Long hotelId);

  Long getAvailableRoomsCount(Long hotelId);
}
