package com.movierec.recommendationservice.restcontroller;

//import com.movierec.ratingservice.client.MovieCLient;
import com.movierec.recommendationservice.entity.Recommendation;
import com.movierec.recommendationservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RecommendRestcontroller {
    @Autowired
    RecommendationRepository repository;
//    @Autowired
//    private MovieCLient movieCLient;

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
    public void RecommendMovieByGenre(@RequestParam("listGenreId") List<Integer> listGenreId){
//        System.out.println(movieCLient.getListMovieByListGenre(listGenreId));
    }

}
