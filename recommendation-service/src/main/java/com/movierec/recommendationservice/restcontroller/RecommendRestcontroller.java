package com.movierec.recommendationservice.restcontroller;

import com.movierec.recommendationservice.client.MovieClient;
import com.movierec.recommendationservice.entity.Movie;
import com.movierec.recommendationservice.entity.Recommendation;
import com.movierec.recommendationservice.repository.RecommendationRepository;
import com.movierec.recommendationservice.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@Slf4j
public class RecommendRestcontroller {
    @Autowired
    RecommendationRepository repository;
    @Autowired
    private RecommendationService service;

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

    @GetMapping("/genres")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> RecommendMovieByGenre(@RequestParam("listGenreId") List<Integer> listGenreId){
        return service.recommendMovie(listGenreId);
    }

}
