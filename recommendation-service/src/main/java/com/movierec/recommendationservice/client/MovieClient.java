package com.movierec.recommendationservice.client;

import com.movierec.recommendationservice.entity.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "movie-service")
public interface MovieClient {
    @GetMapping("/api/movie/genres")
    List<Movie>  getListMovieByListGenre(@RequestParam("listGenreId") List<Integer> listGenreId);
}
