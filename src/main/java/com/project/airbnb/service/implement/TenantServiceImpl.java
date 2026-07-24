package com.project.airbnb.service.implement;

import com.project.airbnb.dto.request.TenantRequest;
import com.project.airbnb.dto.request.TenantUpdateReq;
import com.project.airbnb.dto.response.HotelResponse;
import com.project.airbnb.dto.response.TenantResponse;
import com.project.airbnb.entities.TenantEntity;
import com.project.airbnb.mapper.HotelMapper;
import com.project.airbnb.mapper.TenantMapper;
import com.project.airbnb.repository.TenantRepository;
import com.project.airbnb.service.interfaces.TenantService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

  private final TenantRepository repo;

  @Override
  @Transactional
  public TenantResponse addTenant(TenantRequest tenantRequest) {
    if (repo.existsByEmail(tenantRequest.email())) {
      throw new IllegalStateException("Email already exists");
    }

    TenantEntity tenant = TenantMapper.toEntity(tenantRequest);
    TenantEntity savedTenant = repo.save(tenant);
    return toResponse(savedTenant);
  }

  @Override
  @Transactional(readOnly = true)
  public TenantResponse getTenantById(Long id) {
    return toResponse(getTenantOrThrow(id));
  }

  @Override
  @Transactional(readOnly = true)
  public List<TenantResponse> getTenants() {
    return repo.findAll().stream().map(this::toResponse).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public TenantResponse getTenantByEmail(String email) {
    TenantEntity tenant =
        repo.findByEmail(email).orElseThrow(() -> new RuntimeException("Tenant not found"));
    return toResponse(tenant);
  }

  @Override
  @Transactional
  public TenantResponse updateTenant(TenantUpdateReq request) {
    TenantEntity tenant = getTenantOrThrow(request.tenantId());

    tenant.setFirstName(request.firstName());
    tenant.setLastName(request.lastName());
    tenant.setMobile(request.mobile());
    tenant.setPassword(request.password());

    return toResponse(tenant);
  }

  @Override
  @Transactional
  public void deleteTenantById(Long id) {
    TenantEntity tenant = getTenantOrThrow(id);
    repo.delete(tenant);
  }

  @Override
  @Transactional(readOnly = true)
  public List<HotelResponse> getHotelsByTenant(Long id) {
    TenantEntity tenant = getTenantOrThrow(id);
    return tenant.getHotels().stream().map(HotelMapper::toResponse).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public boolean ownsHotel(Long tenantId, Long hotelId) {
    TenantEntity tenant = getTenantOrThrow(tenantId);
    return tenant.getHotels().stream().anyMatch(hotel -> hotel.getHotelId() == hotelId);
  }

  @Override
  @Transactional(readOnly = true)
  public Long getTotalHotels(Long tenantId) {
    TenantEntity tenant = getTenantOrThrow(tenantId);
    return (long) tenant.getHotels().size();
  }

  @Override
  public BigDecimal getTotalRevenue(Long tenantId) {
    throw new UnsupportedOperationException("Tenant revenue is not supported yet");
  }

  private TenantResponse toResponse(TenantEntity tenant) {
    return TenantMapper.toResponse(tenant);
  }

  private TenantEntity getTenantOrThrow(Long id) {
    return repo.findById(id).orElseThrow(() -> new RuntimeException("Tenant not found"));
  }
}
