package com.stack.stack_over.Dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChangePasswordUserDto {
    private Long id;

    private String password;

    private String validatePassword;
}
