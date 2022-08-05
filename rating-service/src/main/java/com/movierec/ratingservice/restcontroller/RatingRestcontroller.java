package com.movierec.ratingservice.restcontroller;

import com.movierec.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rating")
public class RatingRestcontroller {
    @Autowired
    private RatingService service;

    public List<Rating> getAll(){

    }
}
