package com.project.airbnb.dto.response;

import com.project.airbnb.enums.UserStatus;

public record UserStatusRes(String username, UserStatus oldStatus, UserStatus newStatus) {}
