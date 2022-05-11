package com.stack.stack_over.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stack.stack_over.Model.Role;
import com.stack.stack_over.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder

public class RoleDto {

    private Long id;
    private String name;
    @JsonIgnore
    private UserDto user;
    public static RoleDto fromEntity(Role role)
    {
        if (role ==null)
        {
            return null;
        }
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
    public static Role toEntity(RoleDto roleDto)
    {
        if (roleDto ==null)
        {
            return null;
        }
        Role roles = new Role();
        roles.setId(roleDto.getId());
        roles.setName(roleDto.getName());
        roles.setUser(UserDto.toEntity(roleDto.getUser()));
        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
