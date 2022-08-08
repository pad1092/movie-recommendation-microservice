package com.movierec.recommendationservice.entity;

import lombok.Data;

import java.util.List;

@Data
public class Movie {
    private int movieId;
    private String name;
    private String country;
    private List<Genre> genres;
}
