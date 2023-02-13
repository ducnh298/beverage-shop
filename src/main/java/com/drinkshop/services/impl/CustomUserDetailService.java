package com.drinkshop.services.impl;

import com.drinkshop.model.User;
import com.drinkshop.repository.UserRepository;
import com.drinkshop.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            System.out.println("User not found "+username);
            throw new UsernameNotFoundException("User "+username+" not found in the database!");
        }
        System.out.println("Found user: "+username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserType().name()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,user.getPassword(),grantedAuthorities);
        System.out.println(userDetails);
        return userDetails;
    }
}
