package com.ecommerce.project.controller;

import com.ecommerce.project.Entity.User;
import com.ecommerce.project.dto.User.UserDto;
import com.ecommerce.project.dto.auth.AuthDto;
import com.ecommerce.project.dto.auth.AuthResponseDto;
import com.ecommerce.project.dto.base.Response;
import com.ecommerce.project.service.security.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v10/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid @RequestBody UserDto payload){
        System.out.println("REGISTER HIT: " + payload);
        AuthResponseDto dto = authService.register(payload);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("201","success","successfully registed user",dto));
    }
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody AuthDto payload){
        AuthResponseDto dto = authService.login(payload);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.success("200","success","successfully login",dto));
    }
}
