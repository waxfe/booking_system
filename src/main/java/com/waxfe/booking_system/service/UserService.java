package com.waxfe.booking_system.service;

import com.waxfe.booking_system.UserRole;
import com.waxfe.booking_system.config.SecurityConfig;
import com.waxfe.booking_system.dto.user.UserRequest;
import com.waxfe.booking_system.dto.user.UserResponse;
import com.waxfe.booking_system.entity.UserEntity;
import com.waxfe.booking_system.mappers.UserMapper;
import com.waxfe.booking_system.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final static Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, UserMapper mapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    public List<UserResponse> getAllUsers()
    {
        List<UserEntity> allEntities = repository.findAll();
        return allEntities.stream().map(mapper::toDomain).toList();
    }

    public UserResponse getUserById(Long id)
    {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such user with id:" + id));
        return mapper.toDomain(entity);
    }

    public UserResponse createUser(UserRequest request)
    {
        UserEntity entity = mapper.toEntity((request));
        entity.setPassword(encoder.encode(request.getPassword()));
        entity.setRole(UserRole.USER);
        entity.setCreatedAt(LocalDateTime.now());
        var saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    public UserResponse updateUser(Long id, UserRequest request)
    {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such user with id:" + id));

        entity.setEmail(request.getEmail());
        entity.setFullName(request.getFullName());
        var saved = repository.save(entity);
        return mapper.toDomain(saved);
    }
}
