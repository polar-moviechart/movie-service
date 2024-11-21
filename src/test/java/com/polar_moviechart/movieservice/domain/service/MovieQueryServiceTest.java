package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.MovieTestConfig;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDetailsDto;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MovieQueryServiceTest extends MovieTestConfig {

    @Autowired private MovieQueryService movieQueryService;

    @DisplayName("영화가 존재할 때 이에 맞는 dto를 반환한다.")
    @Test
    void getMovieSuccessCase() {
        // given
        int code = 1;
        // when
        MovieDetailsDto movie = movieQueryService.getMovie(code);
        // then
        Assertions.assertThat(movie.getCode()).isEqualTo(code);
    }

    @DisplayName("영화가 존재하지 않으면 예외를 반환한다.")
    @Test
    void getMovieFailCase() {
        // given
        int code = -1;
        // when // then
        MovieBusinessException exception = assertThrows(MovieBusinessException.class,
                () -> movieQueryService.getMovie(code));
        assertEquals("M101", exception.getCode());
    }

    @DisplayName("영화가 존재할 때 아무 일도 일어나지 않는다.")
    @Test
    void validateExistsSuccessCase() {
        // given
        int code = 1;
        // when // then
        movieQueryService.validateExists(code);
    }

    @DisplayName("영화가 존재하지 않을 때 예외가 발생한다.")
    @Test
    void validateExistsSFailCase() {
        // given
        int code = -1;
        // when // then
        assertThrows(MovieBusinessException.class,
                () -> movieQueryService.validateExists(code));
    }
}