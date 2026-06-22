package com.ecommerce.project.Service;

import com.ecommerce.project.Entity.User;
import com.ecommerce.project.Mapper.UserMapper;
import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.Model.BaseResponseModelWithData;
import com.ecommerce.project.Repository.UserRepository;
import com.ecommerce.project.dto.User.UserDto;
import com.ecommerce.project.dto.User.UserResponseDto;
import com.ecommerce.project.exception.Model.DuplicateResourceException;
import com.ecommerce.project.exception.Model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper mapper;

    public ResponseEntity<BaseResponseModel> createUser(UserDto payload){
        if(userRepository.existsByName(payload.getName())){
            throw new DuplicateResourceException("user already existed");
        }
        if (userRepository.existsByEmail(payload.getEmail())){
            throw new DuplicateResourceException("email already existed");
        }
        User user = mapper.toEntity(payload);

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseModel("success","Successfully Created User"));
    }
    public ResponseEntity<BaseResponseModelWithData> listUser(){
        List<User> users = userRepository.findAll();
        List<UserResponseDto> dtos = mapper.toDtoList(users);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModelWithData("success","successfully retrieve users",dtos));
    }
    public ResponseEntity<BaseResponseModelWithData> getUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("user not found with id :"  +userId));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BaseResponseModelWithData("success","successfully retrieved user id:",userId));
    }
    public ResponseEntity<BaseResponseModel> updateUser(UserDto payload,Long userId){
        User existing = userRepository.findById(userId)
        //if user not found show 404
                .orElseThrow(() ->
                        new ResourceNotFoundException("user not found with id : "  +userId));
        mapper.updateEntityFromDto(existing,payload);
        userRepository.save(existing);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully updated user "));
    }
    public ResponseEntity<BaseResponseModel> deleteUser(Long userId){
        if (!userRepository.existsById(userId))
           throw new ResourceNotFoundException("user not found with id :"  +userId);
        //user found,then delete
        userRepository.deleteById(userId);

        //200 OK
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully deleted user"));
    }

}
