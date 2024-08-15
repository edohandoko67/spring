package com.rs.product.satuan;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SatuanNotFoundException extends RuntimeException{
    public SatuanNotFoundException(String message) {
        super(message);
    }
}
