package com.movierec.ratingservice.repository;

import com.movierec.ratingservice.entity.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
}
