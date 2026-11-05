package com.waxfe.booking_system.service;

import com.waxfe.booking_system.BookingStatus;
import com.waxfe.booking_system.dto.booking.BookingRequest;
import com.waxfe.booking_system.dto.booking.BookingResponse;
import com.waxfe.booking_system.entity.BookingEntity;
import com.waxfe.booking_system.entity.RoomEntity;
import com.waxfe.booking_system.entity.UserEntity;
import com.waxfe.booking_system.mappers.BookingMapper;
import com.waxfe.booking_system.repository.BookingRepository;
import com.waxfe.booking_system.repository.RoomRepository;
import com.waxfe.booking_system.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);
    private final BookingMapper mapper;
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public BookingService(BookingMapper mapper, BookingRepository bookingRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.mapper = mapper;
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public List<BookingResponse> getAllBookings() {
        List<BookingEntity> allBookings = bookingRepository.findAll();
        return allBookings.stream().map(mapper::toDomain).toList();
    }

    public BookingResponse getBookingById(Long id) {
        BookingEntity entity = bookingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No such booking with id:" + id)
        );
        return mapper.toDomain(entity);
    }

    public List<BookingResponse> getBookingsByRoom(Long roomId) {
       if(!roomRepository.existsById(roomId)) {
           throw new  EntityNotFoundException("No such room with id:" + roomId);
        }
        List<BookingEntity> allBookingRooms = bookingRepository.findByRoomId(roomId);
        return allBookingRooms.stream().map(mapper::toDomain).toList();
    }

    public BookingResponse createBooking(BookingRequest request) {
        RoomEntity room = roomRepository.findById(request.getRoomId()).orElseThrow(
                () -> new EntityNotFoundException("No such room with id:" + request.getRoomId())
        );
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new EntityNotFoundException("No such user with id:" + request.getUserId())
        );
        if (!room.isActive()) {
            throw new IllegalStateException("Room is not active right now");
        }
        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new IllegalArgumentException("End time must be later than start time");
        }
        if (bookingRepository.existsConflict(room.getId(), request.getStartTime(), request.getEndTime(), BookingStatus.CANCELLED))
        {
            throw new IllegalStateException("Room is already booked for this time");
        }

        BookingEntity entity = mapper.toEntity(request, room, user);

        entity.setVersion(0L);
        entity.setStatus(BookingStatus.CONFIRMED);
        entity.setCreatedAt(LocalDateTime.now());
        var savedEntity = bookingRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    public BookingResponse cancelBooking(Long id) {
        BookingEntity entity = bookingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No such booking with id:" + id)
        );

        if (entity.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Booking is already cancelled");
        }

        entity.setStatus(BookingStatus.CANCELLED);
        var savedEntity = bookingRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}
