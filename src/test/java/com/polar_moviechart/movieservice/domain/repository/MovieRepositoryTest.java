package com.polar_moviechart.movieservice.domain.repository;

import com.polar_moviechart.movieservice.domain.MovieTestConfig;
import com.polar_moviechart.movieservice.domain.entity.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

class MovieRepositoryTest extends MovieTestConfig {
    private static final List<Integer> movieCodes = List.of(1);
    private static final LocalDate releaseDate = LocalDate.of(2004, 1, 1);
    @BeforeAll
    void setUp() {
        initMovies(movieCodes, releaseDate);
    }

    @DisplayName("findByCode() 쿼리 테스트")
    @Test
    void findByCode() {
        // given // when
        Optional<Movie> optionalMovie = movieRepository.findByCode(movieCodes.get(0));
        // then
        Assertions.assertThat(optionalMovie).isPresent();
    }

    @DisplayName("existsByCode() 쿼리 테스트")
    @Test
    void test() {
        // given // whene
        boolean isExists = movieRepository.existsByCode(movieCodes.get(0));
        // then
        Assertions.assertThat(isExists).isTrue();
    }
}