package com.project.airbnb.dto.response;

import com.project.airbnb.enums.Gender;

public record GuestResponse(Long guestId, String name, Long age, Gender gender) {}
