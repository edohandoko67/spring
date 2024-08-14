package com.briancorp.user.Payload;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//Jawaban UTS no. 5
public class RegistInfo {

    @NotNull
    @Length(min = 5, max = 50)
    @Pattern(regexp = "^([a-zA-Z0-9]+)$") //Jawaban UTS no. 8
    private String username;

    @NotNull
    @Length(min = 8, max = 16)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*\\u0021|.*\\u0040|.*\\u0023|.*\\u005F)(.*)$") //Jawaban UTS no. 8
    private String password;

    @Length(max = 50)
    private String name;

    @Length(max = 50)
    private String address;

    public RegistInfo() {

    }

    public RegistInfo(String username, String password, String name, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
