package com.exam.servicecenter.dto;

import com.exam.servicecenter.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {

    private String token;

    private String username;

    private Role role;
}