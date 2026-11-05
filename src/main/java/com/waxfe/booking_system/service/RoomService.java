package com.waxfe.booking_system.service;

import com.waxfe.booking_system.mappers.RoomMapper;
import com.waxfe.booking_system.dto.room.RoomRequest;
import com.waxfe.booking_system.dto.room.RoomResponse;
import com.waxfe.booking_system.entity.RoomEntity;
import com.waxfe.booking_system.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomService {

    private static final Logger log = LoggerFactory.getLogger(RoomService.class);
    private final RoomRepository repository;
    private final RoomMapper mapper;

    public RoomService(RoomRepository repository, RoomMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<RoomResponse> getAllRooms() {
        List<RoomEntity> allEntities = repository.findAll();
        return allEntities.stream().map(mapper::toDomain).toList();
    }

    public RoomResponse getRoomById(Long id) {
        RoomEntity roomEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not such room with id:" + id));
        return mapper.toDomain(roomEntity);
    }

    public RoomResponse createRoom(RoomRequest request) {
        RoomEntity roomEntity = mapper.toEntity(request);
        roomEntity.setActive(true);
        roomEntity.setCreatedAt(LocalDateTime.now());
        var savedEntity = repository.save(roomEntity);
        return mapper.toDomain(savedEntity);
    }

    public RoomResponse updateRoom(Long id, RoomRequest request) {
        RoomEntity roomEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such room with id:" + id));
        roomEntity.setName(request.getName());
        roomEntity.setCapacity(request.getCapacity());
        roomEntity.setLocation(request.getLocation());

        var savedEntity = repository.save(roomEntity);

        return mapper.toDomain(savedEntity);
    }

    public RoomResponse deactivateRoom(Long id) {
        RoomEntity roomEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such room with id:" + id));
        roomEntity.setActive(false);
        var savedEntity = repository.save(roomEntity);
        return mapper.toDomain(savedEntity);
    }

    public RoomResponse activateRoom(Long id) {
        RoomEntity roomEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such room with id:" + id));
        roomEntity.setActive(true);
        var savedEntity = repository.save(roomEntity);
        return mapper.toDomain(savedEntity);
    }
}
