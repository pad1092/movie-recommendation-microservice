package com.movierec.ratingservice.restcontroller;

import com.movierec.ratingservice.entity.Rating;
import com.movierec.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Rating> getListRatingByListMovieId(@RequestParam("movieList") List<Integer> movieIdList){
        System.out.println(movieIdList);
        return null;
//        return service.getListRatingByListMovieId(movieIdList);
    }

}
