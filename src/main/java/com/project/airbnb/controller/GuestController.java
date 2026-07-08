package com.project.airbnb.controller;

import com.project.airbnb.dto.request.GuestRequest;
import com.project.airbnb.dto.request.GuestUpdateReq;
import com.project.airbnb.dto.response.GuestResponse;
import com.project.airbnb.service.interfaces.GuestService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/guests")
@Validated
public class GuestController {

  private final GuestService service;

  @PostMapping
  public ResponseEntity<GuestResponse> addGuest(@Valid @RequestBody GuestRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.addGuest(request));
  }

  @GetMapping("/{guestId}")
  public ResponseEntity<GuestResponse> getByGuestId(@PathVariable Long guestId) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getByGuestId(guestId));
  }

  @GetMapping
  public ResponseEntity<List<GuestResponse>> getGuest(@NotBlank @RequestParam String username) {
    return ResponseEntity.status(HttpStatus.OK).body(service.getGuest(username));
  }

  @PatchMapping
  public ResponseEntity<GuestResponse> updateGuest(@Valid @RequestBody GuestUpdateReq request) {
    return ResponseEntity.status(HttpStatus.OK).body(service.updateGuest(request));
  }

  @DeleteMapping("/{guestId}")
  public ResponseEntity<String> deleteGuest(@PathVariable Long guestId) {
    return ResponseEntity.status(HttpStatus.OK).body(service.deleteGuest(guestId));
  }
}
