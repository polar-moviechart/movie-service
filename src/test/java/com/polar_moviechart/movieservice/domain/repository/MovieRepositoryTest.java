package com.polar_moviechart.movieservice.domain.repository;

import com.polar_moviechart.movieservice.domain.MovieTestConfig;
import com.polar_moviechart.movieservice.domain.entity.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

class MovieRepositoryTest extends MovieTestConfig {

    @DisplayName("findByCode() 쿼리 테스트")
    @Test
    void findByCode() {
        // given
        int movieCode = 1;
        // when
        Optional<Movie> optionalMovie = movieRepository.findByCode(movieCode);
        // then
        Assertions.assertThat(optionalMovie).isPresent();
    }

    @DisplayName("existsByCode() 쿼리 테스트")
    @Test
    void test() {
        // given
        int movieCode = 1;
        // whene
        boolean isExists = movieRepository.existsByCode(movieCode);
        // then
        Assertions.assertThat(isExists).isTrue();
    }
}