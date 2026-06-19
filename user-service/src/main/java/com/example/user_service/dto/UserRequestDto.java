package com.example.user_service.dto;


import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserRequestDto {

        private String name;
        private String email;
        private String password;
        private String role;
        private LocalDateTime createdAt;
}
