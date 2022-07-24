package com.kagire.railway.controllers;

import com.kagire.railway.configs.JwtTokenProvider;
import com.kagire.railway.dto.CustomUserCreateDto;
import com.kagire.railway.dto.CustomUserLoginDto;
import com.kagire.railway.entities.CustomUser;
import com.kagire.railway.repositories.CustomUserRepository;
import com.kagire.railway.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    CustomUserRepository customUserRepository;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody CustomUserLoginDto userDto) throws Exception {
        Map<Object, Object> model = new HashMap<>();
        try {
            String email = userDto.getEmail();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, userDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.createToken(email);
            model.put("email", email);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @PostMapping("/register")
    public CustomUser register(@RequestBody CustomUserCreateDto userDto) {
        return userService.saveUser(userDto);
    }
}
