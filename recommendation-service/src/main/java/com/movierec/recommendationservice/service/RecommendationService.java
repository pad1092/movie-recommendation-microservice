package com.movierec.recommendationservice.service;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.movierec.recommendationservice.client.MovieClient;
import com.movierec.recommendationservice.client.RatingClient;
import com.movierec.recommendationservice.entity.Movie;
import com.movierec.recommendationservice.entity.Recommendation;
import com.movierec.recommendationservice.repository.RecommendationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

@Service
@Slf4j
public class RecommendationService {
    @Autowired
    private RecommendationRepository repository;
    @Autowired
    private MovieClient movieClient;
    @Autowired
    private RatingClient ratingClient;
    @Autowired
    private Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    @Autowired
    private ExecutorService traceableExecutorService;

    public List<Movie> recommendMovie(List<Integer> genreIdList){
        String genreIdStr = "";
        for (Integer id : genreIdList)
            genreIdStr += "-" + id;
        List<Movie> movieList;

        if (repository.findById(genreIdStr).isPresent() == false){
            movieList = getRecommendation(genreIdList);
            Recommendation recommendation = new Recommendation();
            recommendation.setMovieList(movieList);
            recommendation.setCategoryId(genreIdStr);

            // don't save this shit to database
            if (movieList.get(0).getMovieId() != -1 || movieList.get(0).getPoint() <= 100)
                repository.save(recommendation);
            log.info("Recommendation not available, create one: " + recommendation);
        }
        else{
            log.info("Recommendation available");
            movieList = repository.findById(genreIdStr).get().getMovieList();
        }

        return movieList;
    }

    public List<Movie> getRecommendation(List<Integer> genreIdList){
        List<Movie> movieList = getListMovieFromClient(genreIdList);
        if (movieList.get(0).getMovieId() == -1){
            return movieList;
        }

        List<Integer> movieIdList = new ArrayList<>();
        movieList.forEach(movie -> movieIdList.add(movie.getMovieId()));

        Map<Integer, Float> pointOfMovie = getPointOfMovie(movieIdList);

        movieList.forEach(movie -> {
            int movieId = movie.getMovieId();
            if(pointOfMovie.containsKey(movieId))
                movie.setPoint(pointOfMovie.get(movieId));
        });
        movieList.sort(Comparator.comparing(Movie::getNumOfGenre).thenComparing(Movie::getPoint).reversed());
        return movieList;
    }

    public List<Movie> getListMovieFromClient(List<Integer> genreIdList){
        circuitBreakerFactory.configureExecutorService(traceableExecutorService);
        Resilience4JCircuitBreaker movieCircuit = circuitBreakerFactory.create("movie");
        Supplier<List<Movie>> movieSupplier = () -> movieClient.getListMovieByListGenre(genreIdList);
        List<Movie> movieList = movieCircuit.run(movieSupplier, throwable -> handleMovieCLientShutDown());
        return movieList;
    }
    public Map<Integer, Float> getPointOfMovie(List<Integer> movieIdList){
        circuitBreakerFactory.configureExecutorService(traceableExecutorService);
        Resilience4JCircuitBreaker ratingCircuit = circuitBreakerFactory.create("rating");
        Supplier<Map<Integer, Float>> ratingSupplier = () -> ratingClient.getPointOfMovie(movieIdList);
        Map<Integer, Float> pointOfMovie = ratingCircuit.run(ratingSupplier, throwable -> handleRatingCLienShutDown(movieIdList));
        return pointOfMovie;
    }

    private List<Movie> handleMovieCLientShutDown(){
        log.warn("Movie client has problem â");
        Movie movie = new Movie();
        movie.setName("Hệ thống đang bao trì");
        movie.setMovieId(-1);
        List<Movie> l = new ArrayList<>();
        l.add(movie);
        return l;
    }
    private Map<Integer, Float> handleRatingCLienShutDown(List<Integer> movieIdList){
        log.warn("Rating client has problem");
        Map<Integer, Float> pointOfMovie = new HashMap<>();
        movieIdList.forEach(movieId -> {
            pointOfMovie.put(movieId, 1000000.0F);
        });
        return pointOfMovie;
    }
}
