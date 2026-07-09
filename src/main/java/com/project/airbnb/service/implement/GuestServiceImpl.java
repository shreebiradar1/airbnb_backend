package com.project.airbnb.service.implement;

import com.project.airbnb.dto.request.GuestRequest;
import com.project.airbnb.dto.request.GuestUpdateReq;
import com.project.airbnb.dto.response.GuestResponse;
import com.project.airbnb.entities.GuestEntity;
import com.project.airbnb.mapper.GuestMapper;
import com.project.airbnb.repository.GuestRepository;
import com.project.airbnb.service.interfaces.GuestService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

  private final GuestRepository repo;

  @Override
  public GuestResponse addGuest(GuestRequest request) {
    GuestEntity guest = GuestMapper.toEntity(request);

    GuestEntity savedGuest = repo.save(guest);
    return GuestMapper.toResponse(savedGuest);
  }

  @Override
  public GuestResponse getByGuestId(Long id) {
    GuestEntity guest = getGuestOrThrow(id);

    return GuestMapper.toResponse(guest);
  }

  @Override
  public List<GuestResponse> getGuest(String username) {
    return repo.findByUser_Username(username).stream().map(GuestMapper::toResponse).toList();
  }

  @Override
  @Transactional
  public GuestResponse updateGuest(GuestUpdateReq request) {
    GuestEntity guest = getGuestOrThrow(request.guestId());
    GuestEntity savedGuest = GuestMapper.toEntity(request);
    repo.save(savedGuest);
    return GuestMapper.toResponse(savedGuest);
  }

  @Override
  @Transactional
  public String deleteGuest(Long id) {
    GuestEntity guest = getGuestOrThrow(id);
    repo.delete(guest);
    return guest.getName() + " has been deleted";
  }

  private GuestEntity getGuestOrThrow(Long id) {
    return repo.findById(id).orElseThrow(() -> new RuntimeException("Guest not found"));
  }
}
