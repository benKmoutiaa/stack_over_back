package com.stack.stack_over.Validator;

import com.stack.stack_over.Dto.RoleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {
    public static List<String> validate(RoleDto roleDto) {
        List<String> errors = new ArrayList<>();
        if (roleDto == null || !StringUtils.hasLength(roleDto.getName())) {
            errors.add("please fill in the Name");
        }

        return errors;
    }
}
