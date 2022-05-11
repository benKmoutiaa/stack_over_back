package com.stack.stack_over.Dto;

import com.stack.stack_over.Model.User;
import lombok.Builder;


import java.util.Collection;
import java.util.stream.Collectors;

@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Collection<RoleDto> roles;

    private Boolean locked;
    private Boolean enabled;


    public  static UserDto fromEntity(User user) {
        if (user ==null)
        {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .locked(user.getLocked())
                .enabled(user.getEnabled())
                .roles(
                        user.getRoles()!=null?
                                user.getRoles().stream()
                                        .map(RoleDto::fromEntity)
                                        .collect(Collectors.toList()):null
                )
                .build();
    }

    public static User toEntity(UserDto userDto)
    {
        if (userDto ==null)
        {
            return null;
        }
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .locked(userDto.getLocked())
                .enabled(userDto.getEnabled())
                .build();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleDto> roles) {
        this.roles = roles;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
