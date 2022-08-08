package com.movierec.ratingservice.service;

import com.movierec.ratingservice.entity.Rating;
import com.movierec.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class RatingService {
    @Autowired
    private RatingRepository repository;

    public List<Rating> findAll(){
        return repository.findAll();
    }

    public List<Rating> getlistRatingByMovieId(int movieId){
        return repository.findListRatingByMovieId(movieId);
    }

    public List<Rating> getListRatingByListMovieId(List<Integer> movieIdList){
        List<Rating> ratingList = new ArrayList<>();
        movieIdList.forEach(movieId -> {
            ratingList.addAll(repository.findListRatingByMovieId(movieId));
        });
        return ratingList;
    }

    public Map<Integer, Double> getAvgPointOfMovie (List<Integer> movieIdList){
        List<Rating> ratingList = new ArrayList<>();
        movieIdList.forEach(movieId -> {
            ratingList.addAll(repository.findListRatingByMovieId(movieId));
        });

        Map<Integer, Double> map = ratingList.stream().collect(
                Collectors.groupingBy(e -> e.getMovieId(), Collectors.averagingDouble(Rating::getPoint)));
        return map;
    }

    public Rating createRating(Rating rating){
        return repository.save(rating);
    }
}
