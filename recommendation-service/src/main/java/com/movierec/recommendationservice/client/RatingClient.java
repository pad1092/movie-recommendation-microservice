package com.movierec.recommendationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "rating-service")
public interface RatingClient {
    @GetMapping("/api/rating/movies")
    Map<Integer, Float> getPointOfMovie(@RequestParam("movieList")List<Integer> movieIdList);
}
