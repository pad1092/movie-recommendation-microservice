package com.movierec.ratingservice.service;

import com.movierec.ratingservice.entity.Rating;
import com.movierec.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository repository;

    public List<Rating> findAll(){
        return repository.findAll();
    }

    public Rating getlistRatingByMovieId(int movieId){
        return repository.findByMovieId(movieId);
    }

    public List<Rating> getListRatingByListMovieId(List<Integer> movieIdList){
        List<Rating> ratingList = new ArrayList<>();
        movieIdList.forEach(movieId -> {
            ratingList.add(repository.findByMovieId(movieId));
        });
        return ratingList;
    }
}
