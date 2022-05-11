package com.stack.stack_over.Controller;

import com.stack.stack_over.Controller.api.RegistrationApi;
import com.stack.stack_over.Dto.Auth.RegistrationRequest;
import com.stack.stack_over.Service.Impl.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationController implements RegistrationApi {


    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public String register(RegistrationRequest request) {
        return registrationService.register(request);
    }

    @Override
    public String confirm(String token) {
        return registrationService.confirmToken(token);
    }
}
