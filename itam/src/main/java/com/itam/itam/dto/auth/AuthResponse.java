package com.itam.itam.dto.auth;

import com.itam.itam.dto.user.UserResponse;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthResponse {
    private String token;
    private String tokenType;
    private UserResponse user;
}