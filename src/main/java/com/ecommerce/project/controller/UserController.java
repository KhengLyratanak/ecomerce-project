package com.ecommerce.project.controller;


import com.ecommerce.project.service.UserService;
import com.ecommerce.project.dto.User.ChangeUserPasswordDto;
import com.ecommerce.project.dto.User.UserDto;
import com.ecommerce.project.dto.User.UserResponseDto;
import com.ecommerce.project.dto.base.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v10/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody UserDto payload){
         userService.createdUser(payload);

         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(Response.success("201","success","successfully created user"));
    }
    @GetMapping
    public ResponseEntity<Response> listUser(){

        List<UserResponseDto> users = userService.listUser();

         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","success","successfully retrieved user" ,users));
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<Response> getUser(@PathVariable("user_id") Long userId){
        UserResponseDto user =  userService.getUser(userId);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","success","successfully retrieved user id ",userId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUser(@RequestBody UserDto dto ,@PathVariable("id") Long userId ){
         userService.updateUser(dto, userId);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","success","successfully updated user",userId));
    }

    @PatchMapping("/{id}/change-password")
    public ResponseEntity<Response> changePassword(@PathVariable ("id") Long userId, @RequestBody ChangeUserPasswordDto payload){
         userService.changePassword(payload,userId);

         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","success","successfully changed password"));

    }
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Response> deleteUser(@PathVariable("user_id") Long userId){
         userService.deleteUser(userId);
         return ResponseEntity.status(HttpStatus.OK)
                 .body(Response.success("200","seccess","successfully deleted user",userId));
    }

}
