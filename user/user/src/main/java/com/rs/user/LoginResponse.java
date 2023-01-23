package com.rs.user;

public class LoginResponse {
    private String username;
    private String accessToken;

    private String role;
    public LoginResponse() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LoginResponse(String username, String accessToken, String role) {
        this.username = username;
        this.accessToken = accessToken;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
