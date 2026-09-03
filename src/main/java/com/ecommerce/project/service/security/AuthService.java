package com.ecommerce.project.service.security;

import com.ecommerce.project.Entity.User;
import com.ecommerce.project.Mapper.UserMapper;
import com.ecommerce.project.dto.User.UserDto;
import com.ecommerce.project.dto.User.UserResponseDto;
import com.ecommerce.project.exception.Model.DuplicateResourceException;
import com.ecommerce.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private JwtUtil jwtUtil;

    public String register(UserDto payload){
            if(userRepository.existsByName(payload.getName())){
                throw new DuplicateResourceException("user already existed");
            }
            if (userRepository.existsByEmail(payload.getEmail())){
                throw new DuplicateResourceException("email already existed");
            }
            User user = mapper.toEntity(payload);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User createdUser = userRepository.save(user);
        String token = jwtUtil.generateToken(createdUser);
        return token;
        }
    }

