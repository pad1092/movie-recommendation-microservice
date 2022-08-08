package com.movierec.movieservice.repository;

import com.movierec.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    public Movie findByMovieId(Integer movieId);

    public List<Movie> findAllByGenres_GenreId(int genreId);
}
