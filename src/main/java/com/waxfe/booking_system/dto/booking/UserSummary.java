package com.waxfe.booking_system.dto.booking;

public class UserSummary {

    private final Long id;

    private final String email;

    private final String fullName;

    public UserSummary(Long id, String email, String fullName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
