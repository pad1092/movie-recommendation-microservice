package com.movierec.movieservice.dto;

import com.movierec.movieservice.entity.Genre;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MovieDTO {
    private int movieId;
    private String name;
    private String country;
    private List<Genre> genres;
    private List<Integer> listGenreId;
    private int numOfGenre;
}
