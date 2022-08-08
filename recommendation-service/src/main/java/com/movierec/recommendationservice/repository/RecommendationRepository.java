package com.movierec.recommendationservice.repository;

import com.movierec.recommendationservice.entity.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
}
