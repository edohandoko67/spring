package com.rs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExampleController {

    @GetMapping("/data")
    public Map<String,Object> getData() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "Testing");
        response.put("age", 17);
        response.put("address", "Mojokerto");

        return response;
    }
}
