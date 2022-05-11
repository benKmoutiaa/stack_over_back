package com.stack.stack_over.Dto.Auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

    private String accesToken;

}
