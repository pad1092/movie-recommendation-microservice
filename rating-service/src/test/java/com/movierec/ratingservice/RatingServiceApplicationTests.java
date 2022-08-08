package com.movierec.ratingservice;

import com.movierec.ratingservice.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RatingServiceApplicationTests {

    @Autowired
    RatingService service;
    @Test
    void contextLoads() {
    }

    @Test
    void test(){

    }
}
