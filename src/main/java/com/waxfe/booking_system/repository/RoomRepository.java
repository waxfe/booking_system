package com.waxfe.booking_system.repository;

import com.waxfe.booking_system.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
