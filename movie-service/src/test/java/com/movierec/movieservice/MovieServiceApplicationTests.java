package com.movierec.movieservice;

import com.movierec.movieservice.dto.MovieDTO;
import com.movierec.movieservice.entity.Movie;
import com.movierec.movieservice.repository.GenreRepository;
import com.movierec.movieservice.repository.MovieRepository;
import com.movierec.movieservice.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class MovieServiceApplicationTests {
	@Autowired
	private MovieRepository repository;
	@Autowired
	private MovieService service;
	@Test
	void contextLoads() {
	}
	@Test
	void test(){
		List<Integer> list = Arrays.asList(new Integer[]{1,3,5,4});
		service.getListMovieByGenre(list);
	}
}
