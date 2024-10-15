package com.polar_moviechart.movieservice.domain.repository;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @DisplayName("리파지토리 및 데이터베이스 연결 테스트")
    @Test
    void test() {
        // given // when
        Optional<Movie> optionalMovie = movieRepository.findById(1L);
        // then
        Assertions.assertThat(optionalMovie).isPresent();
    }
}