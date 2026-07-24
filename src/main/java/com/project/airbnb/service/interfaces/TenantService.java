package com.project.airbnb.service.interfaces;

import com.project.airbnb.dto.request.TenantRequest;
import com.project.airbnb.dto.request.TenantUpdateReq;
import com.project.airbnb.dto.response.HotelResponse;
import com.project.airbnb.dto.response.TenantResponse;
import java.math.BigDecimal;
import java.util.List;

public interface TenantService {
  TenantResponse addTenant(TenantRequest tenantRequest);

  TenantResponse getTenantById(Long id);

  List<TenantResponse> getTenants();

  TenantResponse getTenantByEmail(String email);

  TenantResponse updateTenant(TenantUpdateReq request);

  void deleteTenantById(Long id);

  //  void deactivateTenant(Long id);

  List<HotelResponse> getHotelsByTenant(Long id);

  boolean ownsHotel(Long tenantId, Long hotelId);

  Long getTotalHotels(Long tenantId);

  BigDecimal getTotalRevenue(Long tenantId);
}
