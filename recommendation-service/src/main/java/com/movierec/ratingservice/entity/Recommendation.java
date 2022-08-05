package com.movierec.ratingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "recomendation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Recommendation {
    @Id
    private String categoryId;
    private List<Integer> movieId;
}
