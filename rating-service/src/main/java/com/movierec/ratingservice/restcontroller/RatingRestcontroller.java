package com.movierec.ratingservice.restcontroller;

import com.movierec.ratingservice.entity.Rating;
import com.movierec.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rating")
public class RatingRestcontroller {
    @Autowired
    private RatingService service;

    @GetMapping
    public List<Rating> getAll(){
        return service.findAll();
    }

    @GetMapping("/movie")
    @ResponseStatus(HttpStatus.OK)
    public Map<Integer, Double> getListRatingByListMovieId(@RequestParam("movieList") List<Integer> movieIdList){
        return service.getAvgPointOfMovie(movieIdList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rating createRating(@RequestBody Rating rating){
        return service.createRating(rating);
    }
}
