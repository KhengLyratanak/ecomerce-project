package com.ecommerce.project.Mapper;

import com.ecommerce.project.Entity.User;
import com.ecommerce.project.dto.User.ChangeUserPasswordDto;
import com.ecommerce.project.dto.User.UserDto;
import com.ecommerce.project.dto.User.UserResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User toEntity(UserDto dto){
        User entity = new User();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setRole(dto.getRole());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        return entity;
    }
    public void updateEntityFromDto(User entity,UserDto dto){
        if (entity == null || dto == null){
            return;
        }
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setRole(dto.getRole());
    }
    public UserResponseDto toDto(User entity){
        UserResponseDto dto = new UserResponseDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setRole(entity.getRole());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setCreatedAt(entity.getUpdatedAt());
        return dto;
    }
    public List<UserResponseDto> toDtoList(List<User> entities){
        if (entities == null || entities.isEmpty()){
            return new ArrayList<>();
        }
        return entities.stream()
                .map(user -> this.toDto(user))
                .collect(Collectors.toList());
    }
    public void updateEntityChangePassword(User entity,String password){
        entity.setPassword(password);
    }
    }

