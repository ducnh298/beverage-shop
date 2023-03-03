package com.drinkshop.dto;

import lombok.Data;

@Data
public class JWTRequest {
    private String username;
    private String password;
}
