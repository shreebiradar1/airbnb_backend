package com.project.airbnb.controller;

import com.project.airbnb.dto.request.TenantRequest;
import com.project.airbnb.dto.request.TenantUpdateReq;
import com.project.airbnb.dto.response.HotelResponse;
import com.project.airbnb.dto.response.TenantResponse;
import com.project.airbnb.service.interfaces.TenantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tenants")
@Validated
public class TenantController {

  private final TenantService tenantService;

  @PostMapping
  public ResponseEntity<TenantResponse> addTenant(@Valid @RequestBody TenantRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(tenantService.addTenant(request));
  }

  @GetMapping("/{tenantId}")
  public ResponseEntity<TenantResponse> getTenantById(@PathVariable Long tenantId) {
    return ResponseEntity.status(HttpStatus.OK).body(tenantService.getTenantById(tenantId));
  }

  @GetMapping
  public ResponseEntity<List<TenantResponse>> getTenants() {
    return ResponseEntity.status(HttpStatus.OK).body(tenantService.getTenants());
  }

  @GetMapping("/email")
  public ResponseEntity<TenantResponse> getTenantByEmail(@NotBlank @RequestParam String email) {
    return ResponseEntity.status(HttpStatus.OK).body(tenantService.getTenantByEmail(email));
  }

  @PutMapping
  public ResponseEntity<TenantResponse> updateTenant(@Valid @RequestBody TenantUpdateReq request) {
    return ResponseEntity.status(HttpStatus.OK).body(tenantService.updateTenant(request));
  }

  @DeleteMapping("/{tenantId}")
  public ResponseEntity<Void> deleteTenantById(@PathVariable Long tenantId) {
    tenantService.deleteTenantById(tenantId);
    return ResponseEntity.noContent().build();
  }

  //  @PatchMapping("/{tenantId}/deactivate")
  //  public ResponseEntity<Void> deactivateTenant(@PathVariable Long tenantId) {
  //    tenantService.deactivateTenant(tenantId);
  //    return ResponseEntity.noContent().build();
  //  }

  @GetMapping("/{tenantId}/hotels")
  public ResponseEntity<List<HotelResponse>> getHotelsByTenant(@PathVariable Long tenantId) {
    return ResponseEntity.status(HttpStatus.OK).body(tenantService.getHotelsByTenant(tenantId));
  }

  @GetMapping("/{tenantId}/hotels/{hotelId}/ownership")
  public ResponseEntity<Boolean> ownsHotel(
      @PathVariable Long tenantId, @PathVariable Long hotelId) {
    return ResponseEntity.status(HttpStatus.OK).body(tenantService.ownsHotel(tenantId, hotelId));
  }

  @GetMapping("/{tenantId}/hotels/count")
  public ResponseEntity<Long> getTotalHotels(@PathVariable Long tenantId) {
    return ResponseEntity.status(HttpStatus.OK).body(tenantService.getTotalHotels(tenantId));
  }

  @GetMapping("/{tenantId}/revenue")
  public ResponseEntity<BigDecimal> getTotalRevenue(@PathVariable Long tenantId) {
    return ResponseEntity.status(HttpStatus.OK).body(tenantService.getTotalRevenue(tenantId));
  }
}
