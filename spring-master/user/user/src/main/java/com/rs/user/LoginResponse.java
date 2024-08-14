package com.rs.user;

import com.rs.auth.MetaData;

import java.lang.invoke.MethodType;

public class LoginResponse {
//    private String username;
//    private String accessToken;
//    private String role;
    private MetaData metaData;

    private LoginInfo response;

    public LoginResponse() {
    }

    public LoginResponse(MetaData metaData, LoginInfo response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public LoginInfo getResponse() {
        return response;
    }

    public void setResponse(LoginInfo response) {
        this.response = response;
    }

    //    public String getRole() {
//        return role;
//    }
//    public void setRole(String role) {
//        this.role = role;
//    }
//    public LoginResponse(MetaData metaData, String username, String accessToken, String role) {
//        this.metaData = metaData;
//        this.username = username;
//        this.accessToken = accessToken;
//        this.role = role;
//    }
//
//    public MetaData getMetaData() {
//        return metaData;
//    }
//
//    public void setMetaData(MetaData metaData) {
//        this.metaData = metaData;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getAccessToken() {
//        return accessToken;
//    }
//
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
}
