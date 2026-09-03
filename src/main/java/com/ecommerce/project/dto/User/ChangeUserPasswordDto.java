package com.ecommerce.project.dto.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangeUserPasswordDto {

    @NotNull(message = "old password is required")
    @Size(min = 8,max = 20,message = "Password must be beetween 8 to 20 charecter")
    private String oldPassword;

    @NotNull(message = "confirm password is required")
    @Size(min = 8,max = 20,message = "Password must be beetween 8 to 20 charecter")
    private String confirmPassword;

    @NotNull(message = "new password is required")
    @Size(min = 8,max = 20,message = "Password must be beetween 8 to 20 charecter")
    private String newPassword;
}
