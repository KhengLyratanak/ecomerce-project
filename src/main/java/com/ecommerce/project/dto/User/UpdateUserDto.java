package com.ecommerce.project.dto.User;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDto {
    @NotNull(message = "user name is required")
    @Size(min = 4,max = 30,message = "username must be between 4 and 30 charecters")
    private String name;

    @NotNull(message = "age is required")
    @Min(value = 18,message = "age must be atleast 18")
    private Integer age;

    @NotNull(message = "location is required")
    @Size(min = 5,max = 50,message = "address must be between 5 and 50 charecters")
    private String address;

    @NotNull(message = "phone number is required")
    @Size(min = 8,max = 12,message = "phone number must be between 8-12")
    private String phone;

    private String role= "USER";
}


