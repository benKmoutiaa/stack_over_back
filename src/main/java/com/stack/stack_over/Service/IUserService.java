package com.stack.stack_over.Service;

import com.stack.stack_over.Dto.ChangePasswordUserDto;
import com.stack.stack_over.Dto.RoleDto;
import com.stack.stack_over.Dto.UserDto;
import com.stack.stack_over.Model.User;

import java.util.List;

public interface IUserService {
    User save(UserDto dto);
    UserDto findById(Long id);
    RoleDto saveRole(RoleDto role);
    void addRoleToUser(String username,String roleName);
    //UserDto getUser(String username);
    List<UserDto> findAll();
    void delete(Long id);
    UserDto findByEmail(String email);
    UserDto changerPassword(ChangePasswordUserDto dto);
    String signUpUser(UserDto dto);
    int enableAppUser(String email);
}
