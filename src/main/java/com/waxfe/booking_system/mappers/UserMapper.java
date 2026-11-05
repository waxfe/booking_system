package com.waxfe.booking_system.mappers;

import com.waxfe.booking_system.dto.user.UserRequest;
import com.waxfe.booking_system.dto.user.UserResponse;
import com.waxfe.booking_system.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserRequest request) {

        UserEntity entity = new UserEntity();
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setFullName(request.getFullName());

        return entity;
    }

    public UserResponse toDomain(UserEntity response) {
        return new UserResponse(
                response.getId(),
                response.getEmail(),
                response.getFullName(),
                response.getRole(),
                response.getCreatedAt()
        );
    }
}
