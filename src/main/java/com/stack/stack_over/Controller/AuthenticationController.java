package com.stack.stack_over.Controller;

import com.stack.stack_over.Controller.api.AuthenticationApi;
import com.stack.stack_over.Dto.Auth.AuthenticationRequest;
import com.stack.stack_over.Dto.Auth.AuthenticationResponse;
import com.stack.stack_over.Service.Auth.ApplicationUserDetailService;
import com.stack.stack_over.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class AuthenticationController implements AuthenticationApi {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Autowired
    private ApplicationUserDetailService userDetailService;
    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
       authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),
                      request.getPassword()
              )
        );
       final UserDetails userDetails= userDetailService.loadUserByUsername(request.getEmail());
       final String jwt= jwtUtil.generateToken((User)userDetails);

       return ResponseEntity.ok(AuthenticationResponse.builder().accesToken(jwt).build());
    }

}

