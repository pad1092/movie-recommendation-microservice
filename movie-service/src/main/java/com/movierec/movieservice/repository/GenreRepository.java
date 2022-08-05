package com.movierec.movieservice.repository;

import com.movierec.movieservice.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    public Genre findByGenreId(int id);
}
