package com.movierec.ratingservice.restcontroller;

import com.movierec.ratingservice.entity.Recommendation;
import com.movierec.ratingservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RecommendRestcontroller {
    @Autowired
    RecommendationRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Recommendation> getAll(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recommendation saveRating(@RequestBody Recommendation recommendation){
        return repository.save(recommendation);
    }

}
