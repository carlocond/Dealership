package com.dealership.Dealership.mapper;

import com.dealership.Dealership.dto.user.UserRequestDTO;
import com.dealership.Dealership.dto.user.UserResponseDTO;
import com.dealership.Dealership.entity.User;

public final class UserMapper {

    private UserMapper() {
    }

    // DTO -> Entity (per create)
    public static User toEntity(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .fname(dto.getFname())
                .lname(dto.getLname())
                .email(dto.getEmail())
                // la password viene ancora raw qui, la encoderà il servizio
                .password(dto.getPassword())
                .build();
    }

    // Update di un entity esistente da DTO
    public static void updateEntity(User entity, UserRequestDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        entity.setFname(dto.getFname());
        entity.setLname(dto.getLname());
        entity.setEmail(dto.getEmail());
        // Se vuoi gestire la password solo quando non è null:
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(dto.getPassword());
        }
    }

    // Entity -> ResponseDTO
    public static UserResponseDTO toResponseDTO(User entity) {
        if (entity == null) {
            return null;
        }

        return UserResponseDTO.builder()
                .id(entity.getId())
                .fname(entity.getFname())
                .lname(entity.getLname())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }
}

