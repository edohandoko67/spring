package com.rs.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

public class LoginInfo {
    @NotNull
    @Length(min = 5, max = 50)
    private String username;

    @NotNull
    @Length(min = 8, max = 16)
    private String password;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(max = 16)
    @Nullable
    private String number;
    private String accessToken;
    private String role;

//    public LoginInfo(String username, String password, String accessToken, String role) {
//        this.username = username;
//        this.password = password;
//        this.accessToken = accessToken;
//        this.role = role;
//    }

    public LoginInfo(String username, String password, String role, String accessToken, @Nullable String number) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.accessToken = accessToken;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(@Nullable String number) {
        this.number = number;
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