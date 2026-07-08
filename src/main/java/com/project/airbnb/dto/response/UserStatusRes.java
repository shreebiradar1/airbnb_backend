package com.project.airbnb.dto.response;

import com.project.airbnb.enums.UserStatus;

public record UserStatusRes(
    Long userId, String username, UserStatus oldStatus, UserStatus newStatus) {}
