package com.movierec.userservice.service;

import com.movierec.userservice.Entity.User;
import com.movierec.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User createUser(User user){
        return repository.save(user);
    }
}
