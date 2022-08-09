package com.movierec.userservice.service;

import com.movierec.userservice.Entity.User;
import com.movierec.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private StreamBridge streamBridge;

    public User createUser(User user){
        String email = user.getEmail();
        streamBridge.send("notificationEventSupplier-out-0", email);
        return repository.save(user);
    }
}
