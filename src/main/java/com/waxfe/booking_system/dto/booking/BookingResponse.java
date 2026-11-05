package com.waxfe.booking_system.dto.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.waxfe.booking_system.BookingStatus;

import java.time.LocalDateTime;

public class BookingResponse {

    private final Long id;

    private final RoomSummary room;

    private final UserSummary user;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private final LocalDateTime startTime;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private final LocalDateTime endTime;

    private final BookingStatus status;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private final LocalDateTime createdAt;

    public BookingResponse(Long id, RoomSummary room, UserSummary user, LocalDateTime startTime, LocalDateTime endTime, BookingStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.room = room;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public RoomSummary getRoom() {
        return room;
    }

    public UserSummary getUser() {
        return user;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
