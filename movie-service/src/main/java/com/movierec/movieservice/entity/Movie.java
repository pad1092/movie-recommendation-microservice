package com.movierec.movieservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private String name;
    private String country;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "movie_genre",
                joinColumns = @JoinColumn(name = "movie_id"),
                inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;
}
