package com.stack.stack_over.Service.Auth;

import com.stack.stack_over.Dto.UserDto;
import com.stack.stack_over.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class ApplicationUserDetailService implements UserDetailsService {

    @Autowired
    private UserServiceImpl service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

      // System.out.println(email);
        UserDto user= service.findByEmail(email);

        List<SimpleGrantedAuthority> authorities= new ArrayList<>();
        user.getRoles().forEach(role->authorities.add(new SimpleGrantedAuthority(role.getName())));

        return  new User(user.getEmail(),user.getPassword(),authorities);

    }

}
