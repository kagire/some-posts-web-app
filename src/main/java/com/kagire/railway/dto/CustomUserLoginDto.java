package com.kagire.railway.dto;

public class CustomUserLoginDto {

    private String email;
    private String password;

    public CustomUserLoginDto() {}

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
}
