package com.movierec.movieservice.service;

import com.movierec.movieservice.dto.MovieDTO;
import com.movierec.movieservice.entity.Genre;
import com.movierec.movieservice.entity.Movie;
import com.movierec.movieservice.repository.GenreRepository;
import com.movierec.movieservice.repository.MovieRepository;
import com.movierec.movieservice.restcontroller.MovieRestcontroller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        log.info("Get movie by ID");
        if (movie != null){
            movie.getGenres().forEach(genre -> genre.setMovies(null));
            return entityToDto(movie);
        }
        return null;
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

    public List<MovieDTO> getListMovieByGenre(List<Integer> genreIdList){
        log.info("Get list movie by list genreId {}", genreIdList);
        List<Movie> movieList = new ArrayList<>();
        genreIdList.forEach(genreId -> {
            movieList.addAll(repository.findAllByGenres_GenreId(genreId));
        });

        Map<Integer, Long> res = movieList.stream()
                .collect(Collectors.groupingBy(Movie::getMovieId, Collectors.counting()));

        List<MovieDTO> movieDTOList = new ArrayList<>();
        res.forEach((k, v) -> {
            Movie movie = repository.findByMovieId(k);
            movie.getGenres().forEach(genre -> genre.setMovies(null));
            MovieDTO movieDTO = entityToDto(movie);
            movieDTO.setNumOfGenre(Math.toIntExact(v));
            movieDTOList.add(movieDTO);
        });
        return movieDTOList;
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
