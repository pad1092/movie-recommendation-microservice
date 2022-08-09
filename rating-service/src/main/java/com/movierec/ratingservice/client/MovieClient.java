package com.movierec.ratingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "movie-service")
public interface MovieClient {
    @GetMapping("/api/movie/{movieId}")
    public String checkMovieExit(@PathVariable("movieId") int movieId);
}
