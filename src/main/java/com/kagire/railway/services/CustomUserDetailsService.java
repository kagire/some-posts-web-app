package com.kagire.railway.services;

import com.kagire.railway.entities.CustomUser;
import com.kagire.railway.entities.CustomUserDetails;
import com.kagire.railway.repositories.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomUserRepository customUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser user = customUserRepository.findUserByEmail(username);
        if (user == null) throw new UsernameNotFoundException("Could not find user \"" + username +"\"");
        return new CustomUserDetails(user);
    }
}