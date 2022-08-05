package com.movierec.ratingservice.repository;

import com.movierec.ratingservice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    public Rating findByMovieId(int id);
}
