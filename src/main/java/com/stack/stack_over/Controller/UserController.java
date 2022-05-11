package com.stack.stack_over.Controller;

import com.stack.stack_over.Controller.api.UserApi;
import com.stack.stack_over.Dto.ChangePasswordUserDto;
import com.stack.stack_over.Dto.UserDto;
import com.stack.stack_over.Model.User;
import com.stack.stack_over.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserApi {
    @Autowired
    private UserServiceImpl userService;
    @Override
    public User save(UserDto dto) {
        return userService.save(dto);
    }

    @Override
    public UserDto findById(Long id) {
        return userService.findById(id);
    }

    @Override
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public UserDto findByEmail(String email) {
        return userService.findByEmail(email);
    }

    @Override
    public UserDto changerPassword(ChangePasswordUserDto dto) {
        return userService.changerPassword(dto);
    }
}
