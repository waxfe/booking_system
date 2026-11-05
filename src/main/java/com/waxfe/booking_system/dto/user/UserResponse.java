package com.waxfe.booking_system.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.waxfe.booking_system.UserRole;

import java.time.LocalDateTime;

public class UserResponse {

    private final Long id;

    private final String email;

    private final String fullName;

    private final UserRole role;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private final LocalDateTime createdAt;

    public UserResponse(Long id, String email, String fullName, UserRole role, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
