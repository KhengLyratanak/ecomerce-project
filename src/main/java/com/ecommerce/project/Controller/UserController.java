package com.ecommerce.project.Controller;

import com.ecommerce.project.Model.BaseResponseModel;
import com.ecommerce.project.Model.BaseResponseModelWithData;
import com.ecommerce.project.Service.UserService;
import com.ecommerce.project.dto.User.ChangeUserPasswordDto;
import com.ecommerce.project.dto.User.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v10/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<BaseResponseModel> createUser(@RequestBody UserDto payload){
        return userService.createUser(payload);
    }
    @GetMapping
    public ResponseEntity<BaseResponseModelWithData> listUser(){
        return userService.listUser();
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<BaseResponseModelWithData> getUser(@PathVariable("user_id") Long userId){
        return userService.getUser(userId);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseModel> updateUser(@RequestBody UserDto dto ,@PathVariable("id") Long userId ){
        return userService.updateUser(dto, userId);
    }
    @PatchMapping("/{id}/change-password")
    public ResponseEntity<BaseResponseModel> changePassword(@PathVariable ("id") Long userId, @RequestBody ChangeUserPasswordDto payload){
        return userService.changePassword(payload,userId);
    }
    @DeleteMapping("/{user_id}")
    public ResponseEntity<BaseResponseModel> deleteUser(@PathVariable("user_id") Long userId){
        return userService.deleteUser(userId);
    }

}
