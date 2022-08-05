package com.movierec.movieservice.repository;

import com.movierec.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    public Movie findByMovieId(Integer movieId);
}
