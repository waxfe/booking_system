package com.waxfe.booking_system.mappers;

import com.waxfe.booking_system.dto.room.RoomRequest;
import com.waxfe.booking_system.dto.room.RoomResponse;
import com.waxfe.booking_system.entity.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomEntity toEntity(RoomRequest request) {

        RoomEntity entity = new RoomEntity();
        entity.setName(request.getName());
        entity.setCapacity(request.getCapacity());
        entity.setLocation(request.getLocation());

        return entity;
    }

    public RoomResponse toDomain(RoomEntity response) {

        return new RoomResponse(
                response.getId(),
                response.getName(),
                response.getCapacity(),
                response.getLocation(),
                response.isActive(),
                response.getCreatedAt()
        );
    }
}
