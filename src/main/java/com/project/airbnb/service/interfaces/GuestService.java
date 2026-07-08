package com.project.airbnb.service.interfaces;

import com.project.airbnb.dto.request.GuestRequest;
import com.project.airbnb.dto.request.GuestUpdateReq;
import com.project.airbnb.dto.response.GuestResponse;
import java.util.List;

public interface GuestService {
  GuestResponse addGuest(GuestRequest request);

  GuestResponse getByGuestId(Long id);

  List<GuestResponse> getGuest(String user);

  GuestResponse updateGuest(GuestUpdateReq request);

  String deleteGuest(Long id);
}
