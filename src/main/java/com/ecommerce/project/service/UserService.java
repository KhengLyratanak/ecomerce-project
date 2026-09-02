package com.ecommerce.project.service;

import com.ecommerce.project.Entity.User;
import com.ecommerce.project.Mapper.UserMapper;
import com.ecommerce.project.repository.UserRepository;
import com.ecommerce.project.dto.User.ChangeUserPasswordDto;
import com.ecommerce.project.dto.User.UserDto;
import com.ecommerce.project.dto.User.UserResponseDto;
import com.ecommerce.project.exception.Model.DuplicateResourceException;
import com.ecommerce.project.exception.Model.ResourceNotFoundException;
import com.ecommerce.project.service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private JwtUtil jwtUtil;

    public void createdUser (UserDto payload){
        if(userRepository.existsByName(payload.getName())){
            throw new DuplicateResourceException("user already existed");
        }
        if (userRepository.existsByEmail(payload.getEmail())){
            throw new DuplicateResourceException("email already existed");
        }
        User user = mapper.toEntity(payload);

        userRepository.save(user);
    }

    public List<UserResponseDto> listUser() {
        List<User> users = userRepository.findAll();
        return mapper.toDtoList(users);
    }

    public  UserResponseDto getUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("user not found with id :"  +userId));
       String token = jwtUtil.generateToken(user);
       System.out.println("Token: " +token);
         return mapper.toDto(user);
    }
    public void updateUser(UserDto payload,Long userId){
        User existing = userRepository.findById(userId)
        //if user not found show 404
                .orElseThrow(() ->
                        new ResourceNotFoundException("user not found with id : "  +userId));
        mapper.updateEntityFromDto(existing,payload);
        userRepository.save(existing);

    }
    public void changePassword( ChangeUserPasswordDto dto,Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("user not found with id :" +userId));
        //if current password not the same with oldpassword
        if (!Objects.equals(user.getPassword(),dto.getOldPassword())){
           throw new ResourceNotFoundException("old password is incorrect , please enter the correct password");
        }
        if (!Objects.equals(dto.getNewPassword(),dto.getConfirmPassword())){
          throw new ResourceNotFoundException("new password and confirm password must be the same");
        }
        mapper.updateEntityChangePassword(user,dto.getConfirmPassword());
        userRepository.save(user);
    }
    public void deleteUser(Long userId){
        if (!userRepository.existsById(userId))
           throw new ResourceNotFoundException("user not found with id :"  +userId);
        //user found,then delete
        userRepository.deleteById(userId);

        //200 OK

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("user not found: " + username);
                });
    }
    }
