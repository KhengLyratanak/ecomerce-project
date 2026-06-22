package com.ecommerce.project.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "User name is required")
    private String name;
    @NotNull(message = "password is required")
    private String password;

    private String address;

    @NotNull(message = "email is required")
    @Email(message = "email must be valid")
    private  String email;
    private String phone;
    private String role= "USER";
}
