package com.waxfe.booking_system.repository;

import com.waxfe.booking_system.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByRoomId(Long roomId);
}
