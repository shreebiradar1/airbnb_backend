package com.project.airbnb.repository;

import com.project.airbnb.entities.RoomEntity;
import com.project.airbnb.enums.RoomStatus;
import com.project.airbnb.enums.RoomType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

  List<RoomEntity> findByHotelHotelId(Long hotelId);

  List<RoomEntity> findByHotelHotelIdAndRoomStatus(Long hotelId, RoomStatus roomStatus);

  List<RoomEntity> findByHotelHotelIdAndRoomType(Long hotelId, RoomType roomType);

  List<RoomEntity> findByHotelHotelIdAndRoomTypeAndCapacity(
      Long hotelId, RoomType roomType, Long capacity);
}
