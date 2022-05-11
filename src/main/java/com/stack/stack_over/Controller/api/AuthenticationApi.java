package com.stack.stack_over.Controller.api;

import com.stack.stack_over.Dto.Auth.AuthenticationRequest;
import com.stack.stack_over.Dto.Auth.AuthenticationResponse;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import static com.stack.stack_over.utils.Constants.AUTHENTICATION_ENDPOINT;


@Api(AUTHENTICATION_ENDPOINT)
public interface AuthenticationApi {


    @PostMapping(value = AUTHENTICATION_ENDPOINT+"/authenticate",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
}
