package com.waxfe.booking_system.controller;

import com.waxfe.booking_system.dto.room.RoomRequest;
import com.waxfe.booking_system.dto.room.RoomResponse;
import com.waxfe.booking_system.service.RoomService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);

    private final RoomService service;


    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms () {
            log.info("Called method getAllRooms");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById (@PathVariable("id") Long id) {
        log.info("Called getRoomById: " + id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getRoomById(id));
    }

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@RequestBody @Valid RoomRequest request) {
        log.info("Called createRoom");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createRoom(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable("id") Long id,
            @RequestBody @Valid RoomRequest request) {
        log.info("Called updateRoom id= {}, update = {}", id, request);
        var updated = service.updateRoom(id, request);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<RoomResponse> deactivateRoom(
            @PathVariable("id") Long id
    ) {
        log.info("Called deactivateRoom with id = {}", id);
        return ResponseEntity.ok(service.deactivateRoom(id));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<RoomResponse> activateRoom(
            @PathVariable("id") Long id
    ) {
        log.info("Called activateRoom with id = {}", id);
        return ResponseEntity.ok(service.activateRoom(id));
    }
}
