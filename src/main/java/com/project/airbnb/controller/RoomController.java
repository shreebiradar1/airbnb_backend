package com.project.airbnb.controller;

import com.project.airbnb.dto.request.RoomRequest;
import com.project.airbnb.dto.response.RoomResponse;
import com.project.airbnb.enums.RoomType;
import com.project.airbnb.service.interfaces.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/rooms")
@Validated
public class RoomController {

  private final RoomService roomService;

  @PostMapping
  public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody RoomRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(request));
  }

  @GetMapping("/{roomId}")
  public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long roomId) {
    return ResponseEntity.status(HttpStatus.OK).body(roomService.getRoomById(roomId));
  }

  @GetMapping
  public ResponseEntity<List<RoomResponse>> getAllRooms() {
    return ResponseEntity.status(HttpStatus.OK).body(roomService.getAllRooms());
  }

  @GetMapping("/hotel/{hotelId}")
  public ResponseEntity<List<RoomResponse>> getRoomsByHotelId(@PathVariable Long hotelId) {
    return ResponseEntity.status(HttpStatus.OK).body(roomService.getRoomByHotelId(hotelId));
  }

  @GetMapping("/hotel/{hotelId}/available")
  public ResponseEntity<List<RoomResponse>> getAvailableRooms(
      @PathVariable Long hotelId,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(roomService.getAvailableRooms(hotelId, checkIn, checkOut));
  }

  @GetMapping("/hotel/{hotelId}/type")
  public ResponseEntity<List<RoomResponse>> getRoomsByType(
      @PathVariable Long hotelId, @RequestParam RoomType roomType) {
    return ResponseEntity.status(HttpStatus.OK).body(roomService.getRoomByType(hotelId, roomType));
  }

  @PutMapping
  public ResponseEntity<RoomResponse> updateRoom(@Valid @RequestBody RoomRequest request) {
    return ResponseEntity.status(HttpStatus.OK).body(roomService.updateRoom(request));
  }

  @PatchMapping("/{roomId}/price")
  public ResponseEntity<RoomResponse> updateRoomPrice(
      @PathVariable Long roomId, @RequestParam @Positive BigDecimal price) {
    return ResponseEntity.status(HttpStatus.OK).body(roomService.updateRoomPrice(roomId, price));
  }

  @DeleteMapping("/{roomId}")
  public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
    roomService.deleteRoom(roomId);
    return ResponseEntity.noContent().build();
  }
}
