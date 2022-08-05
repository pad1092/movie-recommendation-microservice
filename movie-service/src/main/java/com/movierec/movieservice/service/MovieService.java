package com.movierec.movieservice.service;

import com.movierec.movieservice.dto.MovieDTO;
import com.movierec.movieservice.entity.Genre;
import com.movierec.movieservice.entity.Movie;
import com.movierec.movieservice.repository.GenreRepository;
import com.movierec.movieservice.repository.MovieRepository;
import com.movierec.movieservice.restcontroller.MovieRestcontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;
    @Autowired
    private GenreRepository genreRepository;

    public List<MovieDTO> findAll(){
         List<Movie> movieList = repository.findAll();
         List<MovieDTO> movieDTOList = new ArrayList<>();
         movieList.forEach(movie -> {
             movie.getGenres().forEach(genre -> genre.setMovies(null));
             MovieDTO movieDTO = entityToDto(movie);
             movieDTOList.add(movieDTO);
         });
         return movieDTOList;
    }

    public MovieDTO findMovieById(int movieId){
        Movie movie = repository.findByMovieId(movieId);
        movie.getGenres().forEach(genre -> genre.setMovies(null));
        return entityToDto(movie);
    }

    public MovieDTO createMovie(MovieDTO movieDTO){
        List<Genre> genreList = new ArrayList<>();
        movieDTO.getListGenreId().forEach(genreId -> {
            genreList.add(genreRepository.findByGenreId(genreId));
        });
        System.out.println(genreList);
        Movie movie = dtoToEntity(movieDTO);
        movie.setGenres(genreList);
        repository.saveAndFlush(movie);
        return findMovieById(movie.getMovieId());
    }

    private Movie dtoToEntity(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setName(movieDTO.getName());
        movie.setCountry(movieDTO.getCountry());
        movie.setGenres(movieDTO.getGenres());
        return movie;
    }

    private MovieDTO entityToDto(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(movie.getMovieId());
        movieDTO.setName(movie.getName());
        movieDTO.setCountry(movie.getCountry());
        movieDTO.setGenres(movie.getGenres());
        return movieDTO;
    }
}
