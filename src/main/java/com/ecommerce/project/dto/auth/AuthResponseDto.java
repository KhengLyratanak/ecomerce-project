package com.ecommerce.project.dto.auth;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    @JsonProperty("refresh_Token")
    private String refreshToken;
    @JsonProperty("access_Token")
    private String accessToken;
}
