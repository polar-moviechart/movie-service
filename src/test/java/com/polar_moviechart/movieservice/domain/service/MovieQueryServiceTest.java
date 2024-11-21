package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.MovieTestConfig;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDetailsDto;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieQueryServiceTest extends MovieTestConfig {

    @Autowired private MovieQueryService movieQueryService;

    private final static List<Integer> movieCodes = List.of(1);
    private final static LocalDate releaseDate = LocalDate.of(2004, 1, 1);

    @BeforeAll
    void setUp() {
        initMovies(movieCodes, releaseDate);
    }

    @DisplayName("영화가 존재할 때 이에 맞는 dto를 반환한다.")
    @Test
    void getMovieSuccessCase() {
        // given
        Integer movieCode = movieCodes.get(0);
        // when
        MovieDetailsDto movie = movieQueryService.getMovie(movieCode);
        // then
        Assertions.assertThat(movie.getCode()).isEqualTo(movieCode);
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
        // given // when // then
        movieQueryService.validateExists(movieCodes.get(0));
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