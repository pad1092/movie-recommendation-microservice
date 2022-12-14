package com.movierec.movieservice.restcontroller;

import com.movierec.movieservice.dto.MovieDTO;
import com.movierec.movieservice.entity.Movie;
import com.movierec.movieservice.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@Slf4j
public class MovieRestcontroller {
    @Autowired
    MovieService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<MovieDTO> getAllMovie(){
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MovieDTO createMovie(@RequestBody MovieDTO movie){
        return service.createMovie(movie);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{movieId}")
    public MovieDTO findMovieById(@PathVariable("movieId") int movieId){
        return service.findMovieById(movieId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/genres")
    public List<MovieDTO> findALlByListGenreId(@RequestParam("listGenreId") List<Integer> listGenreId){
        return service.getListMovieByGenre(listGenreId);
    }
}
