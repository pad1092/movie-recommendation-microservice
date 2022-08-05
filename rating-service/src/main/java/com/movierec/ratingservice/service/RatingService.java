package com.movierec.ratingservice.service;

import com.movierec.ratingservice.entity.Rating;
import com.movierec.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository repository;

    public List<Rating> findAll(){
        return repository.findAll();
    }

    public List<Rating> sortByPoint(){

    }
}
