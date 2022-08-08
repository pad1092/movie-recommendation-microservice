package com.movierec.userservice.restcontroller;

import com.movierec.userservice.Entity.User;
import com.movierec.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestcontroller {
    @Autowired
    private UserService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User createUser(@RequestBody com.movierec.userservice.Entity.User user){
        return service.createUser(user);
    }
}
