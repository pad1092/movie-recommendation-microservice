package com.movierec.movieservice.dto;

import com.movierec.movieservice.entity.Genre;
import lombok.Data;

import java.util.List;
@Data
public class MovieDTO {
    private int movieId;
    private String name;
    private String country;
    private List<Genre> genres;
    private List<Integer> listGenreId;
}
