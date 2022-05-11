package com.stack.stack_over.Validator;

import com.stack.stack_over.Dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validate(UserDto userDto){
        List<String> errors= new ArrayList<>();
        if(userDto==null)
        {
            errors.add("please fill in the FirstName");
            errors.add("please fill in the LastName");
            errors.add("please fill in the Email");
            errors.add("please fill in the Password");
            return errors;
        }
        if (!StringUtils.hasLength(userDto.getFirstName()))
        {
            errors.add("please fill in the FirstName");
        }
        if (!StringUtils.hasLength(userDto.getLastName()))
        {
            errors.add("please fill in the LastName");
        }
        if (!StringUtils.hasLength(userDto.getEmail()))
        {
            errors.add("please fill in the Email");
        }
        if (!StringUtils.hasLength(userDto.getPassword()))
        {
            errors.add("please fill in the Password");
        }
        return errors;
    }
}
