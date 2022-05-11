package com.stack.stack_over.Controller.api;

import com.stack.stack_over.Dto.Auth.RegistrationRequest;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.stack.stack_over.utils.Constants.REGISTRATION_ENDPOINT;

@Api(REGISTRATION_ENDPOINT)
public interface RegistrationApi {

    @PostMapping(value = REGISTRATION_ENDPOINT+"/register",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
     String register(@RequestBody RegistrationRequest request);

   @GetMapping(value = REGISTRATION_ENDPOINT+"/confirm")
   String confirm(@RequestParam("token") String token);

}
