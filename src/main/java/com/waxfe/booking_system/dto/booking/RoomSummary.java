package com.waxfe.booking_system.dto.booking;

public class RoomSummary {

    private final Long id;

    private final String name;

    private final Integer capacity;

    private final String location;

    public RoomSummary(Long id, String name, Integer capacity, String location) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.location = location;
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
}
