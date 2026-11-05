package com.waxfe.booking_system.mappers;

import com.waxfe.booking_system.dto.booking.BookingRequest;
import com.waxfe.booking_system.dto.booking.BookingResponse;
import com.waxfe.booking_system.dto.booking.RoomSummary;
import com.waxfe.booking_system.dto.booking.UserSummary;
import com.waxfe.booking_system.entity.BookingEntity;
import com.waxfe.booking_system.entity.RoomEntity;
import com.waxfe.booking_system.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingEntity toEntity(BookingRequest request, RoomEntity room, UserEntity user) {
        BookingEntity booking = new BookingEntity();
        booking.setRoom(room);
        booking.setUser(user);
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());

        return booking;
    }

    public BookingResponse toDomain(BookingEntity entity)
    {
        RoomEntity roomEntity = entity.getRoom();
        RoomSummary room = new RoomSummary(roomEntity.getId(), roomEntity.getName(),
                roomEntity.getCapacity(), roomEntity.getLocation());
        UserEntity userEntity = entity.getUser();
        UserSummary user = new UserSummary(userEntity.getId(), userEntity.getEmail(), userEntity.getFullName());
        return new BookingResponse (
                entity.getId(),
                room,
                user,
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
