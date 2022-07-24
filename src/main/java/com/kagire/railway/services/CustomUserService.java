package com.kagire.railway.services;

import com.kagire.railway.dto.CustomUserCreateDto;
import com.kagire.railway.entities.CustomUser;
import com.kagire.railway.repositories.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CustomUserRepository customUserRepository;

    public CustomUser findUserByEmail(String email){
        return customUserRepository.findUserByEmail(email);
    }

    public CustomUser saveUser(CustomUserCreateDto customUserCreateDto){
        CustomUser userExists = customUserRepository.findUserByEmail(customUserCreateDto.getEmail());
        if (userExists != null) throw new BadCredentialsException("Email \": " + customUserCreateDto.getEmail() + "\" already registered");
        CustomUser user = new CustomUser(customUserCreateDto.getUsername(), passwordEncoder.encode(customUserCreateDto.getPassword()), customUserCreateDto.getEmail());
        customUserRepository.save(user);
        return user;
    }
}
