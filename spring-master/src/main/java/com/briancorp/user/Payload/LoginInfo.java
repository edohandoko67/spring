package com.briancorp.user.Payload;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginInfo {

    @NotNull
    @Length(min = 5, max = 50)
    private String username;

    @NotNull
    @Length(min = 8, max = 16)
    private String password;

    public LoginInfo() {

    }

    public LoginInfo(String username, String password) {
        this.username = username;
        this.password = password;
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
}
