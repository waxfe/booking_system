package com.waxfe.booking_system.controller;

import com.waxfe.booking_system.dto.booking.BookingRequest;
import com.waxfe.booking_system.dto.booking.BookingResponse;
import com.waxfe.booking_system.service.BookingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable("id") Long id)
    {
        log.info("Called method getBookingById={}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getBookingById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings()
    {
        log.info("Called method getAllBookings");
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllBookings());
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody @Valid BookingRequest request) {
        log.info("Called method createBooking");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createBooking(request));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(@PathVariable Long id)
    {
        log.info("Called method cancelBooking with id={}", id);
        return ResponseEntity.ok(service.cancelBooking(id));
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByRoom(@PathVariable Long roomId)
    {
        log.info("Called method getBookingsByRoom with id={}", roomId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getBookingsByRoom(roomId));
    }
}
