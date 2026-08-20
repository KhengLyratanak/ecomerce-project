package com.ecommerce.project.dto.User;

import com.ecommerce.project.common.annotation.ValidEnum;
import com.ecommerce.project.common.enums.role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(min = 5,max = 50,message = "address must be between 5 and 50 charecters")
    private String address;

    @NotNull(message = "email is required")
    @Email(message = "email must be valid")
    private  String email;

    @NotNull(message = "phone number is required")
    @Size(min = 8,max = 12,message = "phone number must be between 8-12")
    private String phone;

    @ValidEnum(enumClass = role.class,message = "Role must be in [USER,ADMIN]")
    private String role;
}
