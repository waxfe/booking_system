package com.waxfe.booking_system.controller;

import com.waxfe.booking_system.dto.user.UserRequest;
import com.waxfe.booking_system.dto.user.UserResponse;
import com.waxfe.booking_system.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Called method getAllUsers");
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id)
    {
        log.info("Called getUserById with id={}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request)
    {
        log.info("Called createUser");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest request)
    {
        log.info("Called updateUser with id = {}, update = {}", id, request);
        return ResponseEntity.ok(service.updateUser(id, request));
    }
}
