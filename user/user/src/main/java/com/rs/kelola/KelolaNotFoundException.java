package com.rs.kelola;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class KelolaNotFoundException extends RuntimeException{
    public KelolaNotFoundException(String message){
        super(message);
    }
}
