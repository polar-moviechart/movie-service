package com.polar_moviechart.movieservice.domain.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
class MovieQueryServiceTest {
    @Autowired
    private MovieQueryService movieQueryService;

    @DisplayName("영화를 페이지네이션해서 가져올 수 있다.")
    @Test
    void test() {
        // given
        PageRequest pageable = PageRequest.of(0,  10);
        // when
        List<MovieDto> movies = movieQueryService.getMovies(pageable);
        // then
        Assertions.assertThat(movies.size()).isEqualTo(10);
    }
}