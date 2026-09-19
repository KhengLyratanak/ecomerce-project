package com.ecommerce.project.service.security;

import com.ecommerce.project.Entity.User;
import com.ecommerce.project.Mapper.UserMapper;
import com.ecommerce.project.dto.User.UserDto;
import com.ecommerce.project.dto.User.UserResponseDto;
import com.ecommerce.project.dto.auth.AuthDto;
import com.ecommerce.project.dto.auth.AuthResponseDto;
import com.ecommerce.project.exception.Model.DuplicateResourceException;
import com.ecommerce.project.repository.UserRepository;
import com.ecommerce.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponseDto register(UserDto payload){
            if(userRepository.existsByName(payload.getName())){
                throw new DuplicateResourceException("user already existed");
            }
            if (userRepository.existsByEmail(payload.getEmail())){
                throw new DuplicateResourceException("email already existed");
            }
            User user = mapper.toEntity(payload);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User createdUser = userRepository.save(user);
        String accessToken = jwtUtil.generateToken(createdUser);

        return new AuthResponseDto(accessToken,null);
        }

    public AuthResponseDto login(AuthDto payload){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(payload.getUsername(),payload.getPassword())
        );
        UserDetails userDetails = userService.loadUserByUsername(payload.getUsername());
        String accessToken = jwtUtil.generateToken(userDetails);

        return new AuthResponseDto(accessToken,null);
    }

    }

