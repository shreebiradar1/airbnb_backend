package com.project.airbnb.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.airbnb.dto.summary.UserSummary;
import com.project.airbnb.enums.Role;
import com.project.airbnb.enums.UserStatus;
import java.util.Set;

@JsonPropertyOrder({"id", "username", "summary", "role", "userStatus"})
public record UserResponse(
    Long id, String username, UserSummary summary, Set<Role> role, UserStatus userStatus) {}
