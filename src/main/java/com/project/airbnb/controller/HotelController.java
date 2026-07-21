package com.project.airbnb.controller;

import com.project.airbnb.dto.request.HotelAddRequest;
import com.project.airbnb.dto.request.HotelSearchReq;
import com.project.airbnb.dto.response.HotelResponse;
import com.project.airbnb.dto.response.HotelSearchRes;
import com.project.airbnb.service.interfaces.HotelService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hotels")
@Validated
public class HotelController {
  private final HotelService hotelService;

  @PostMapping
  public ResponseEntity<HotelResponse> addHotel(@Valid @RequestBody HotelAddRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.addHotel(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<HotelResponse> getHotelById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(id));
  }

  @GetMapping
  public ResponseEntity<List<HotelSearchRes>> getHotelDetails(HotelSearchReq request) {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelDetails(request));
  }

  @GetMapping("/active")
  public ResponseEntity<List<HotelResponse>> getActiveHotels() {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.getActiveHotel());
  }

  @PutMapping
  public ResponseEntity<HotelResponse> updateHotel(@Valid @RequestBody HotelAddRequest request) {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.updateHotel(request));
  }

  @DeleteMapping("/{hotelId}")
  public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId) {
    hotelService.deleteHotelById(hotelId);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{hotelId}/activate")
  public ResponseEntity<Void> activateHotelById(@PathVariable Long hotelId) {
    hotelService.activateHotelById(hotelId);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{hotelId}/deactivate")
  public ResponseEntity<Void> deactivateHotelById(@PathVariable Long hotelId) {
    hotelService.deactivateHotelById(hotelId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{hotelId}/rooms/count")
  public ResponseEntity<Long> getTotalRooms(@PathVariable Long hotelId) {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.getTotalRooms(hotelId));
  }

  @GetMapping("/{hotelId}/rooms/available/count")
  public ResponseEntity<Long> getAvailableRoomsCount(@PathVariable Long hotelId) {
    return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAvailableRoomsCount(hotelId));
  }
}
