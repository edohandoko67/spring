package com.rs.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginInfo {
    @NotNull
    @Length(min = 5, max = 50)
    private String username;

    @NotNull
    @Length(min = 8, max = 16)
    private String password;

    private String accessToken;
    private String role;

    public LoginInfo(String username, String password, String role, String accessToken) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}