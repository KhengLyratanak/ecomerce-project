package com.ecommerce.project.Service;

import com.ecommerce.project.Entity.User;
import com.ecommerce.project.Mapper.UserMapper;
import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.Model.BaseResponseModelWithData;
import com.ecommerce.project.Repository.UserRepository;
import com.ecommerce.project.dto.User.ChangeUserPasswordDto;
import com.ecommerce.project.dto.User.UserDto;
import com.ecommerce.project.dto.User.UserResponseDto;
import com.ecommerce.project.exception.Model.DuplicateResourceException;
import com.ecommerce.project.exception.Model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper mapper;

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
        List<UserResponseDto> dtos = mapper.toDtoList(users);
        return mapper.toDtoList(users);
    }

    public  UserResponseDto getUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("user not found with id :"  +userId));
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

}
