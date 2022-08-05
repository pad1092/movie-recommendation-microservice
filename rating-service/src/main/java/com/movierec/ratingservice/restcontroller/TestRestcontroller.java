package com.movierec.ratingservice.restcontroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RefreshScope
public class TestRestcontroller {
    @Value("${app.name}")
    private String name;

    @GetMapping
    public String testAppName(){
        return name;
    }
}
