package com.waxfe.booking_system.dto.room;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class RoomResponse {

    private final Long id;

    private final String name;

    private final Integer capacity;

    private final String location;

    private final Boolean isActive;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private final LocalDateTime createdAt;

    public RoomResponse(Long id, String name, Integer capacity, String location, Boolean isActive, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public Boolean isActive() {
        return isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
