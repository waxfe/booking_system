package com.waxfe.booking_system.repository;

import com.waxfe.booking_system.BookingStatus;
import com.waxfe.booking_system.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByRoomId(Long roomId);

    @Query("""
        SELECT COUNT(b) > 0 from BookingEntity b
                WHERE b.room.id = :roomId
                AND :startTime < b.endTime
                AND b.startTime < :endTime
                AND b.status != :status
        """)
    boolean existsConflict (
            @Param("roomId") Long roomId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("status") BookingStatus status
            );
}