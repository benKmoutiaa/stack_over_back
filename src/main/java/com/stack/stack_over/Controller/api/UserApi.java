package com.stack.stack_over.Controller.api;

import com.stack.stack_over.Dto.ChangePasswordUserDto;
import com.stack.stack_over.Dto.UserDto;
import com.stack.stack_over.Model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import  static com.stack.stack_over.utils.Constants.USER_ENDPOINT;

@Api(USER_ENDPOINT)
public interface UserApi {

    @PostMapping(value = USER_ENDPOINT+"/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value="add user",notes = "add or update user", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message="user add / update"),
            @ApiResponse(code = 400,message="invalid user")
    })
    User save(@RequestBody UserDto dto);


    @GetMapping(value = USER_ENDPOINT+"/{id}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value="find user",notes = "search user by id", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message="user found in the database"),
            @ApiResponse(code = 404,message="user not found in the database")
    })
    UserDto findById(@PathVariable("id") Long id);

    //RoleDto saveRole(RoleDto role);
    //void addRoleToUser(String username,String roleName);
    //UserDto getUser(String username);
    @GetMapping(value = USER_ENDPOINT+"/all",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value="show all user",notes = "show all user", responseContainer = "List<UserDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message="the list of users")
    })
    List<UserDto> findAll();

    @GetMapping(value = USER_ENDPOINT+"/delete/{id}" )
    @ApiOperation(value="delete user",notes = "delete user", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message="deleted user")
    })
    void delete(@PathVariable("id")Long id);

    @GetMapping(value =USER_ENDPOINT + "/find/{email}",produces = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value="find user",notes = "search user by email", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message="user found in the database"),
            @ApiResponse(code = 404,message="user not found in the database")
    })
    UserDto findByEmail(@PathVariable("email") String email);

    @PostMapping(value =USER_ENDPOINT + "/update/password",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto changerPassword(@RequestBody ChangePasswordUserDto dto);

}
