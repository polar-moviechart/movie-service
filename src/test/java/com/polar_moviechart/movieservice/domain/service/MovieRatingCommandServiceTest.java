package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.MovieTestConfig;
import com.polar_moviechart.movieservice.domain.controller.secureapi.UpdateRatingRequest;
import com.polar_moviechart.movieservice.domain.entity.MovieRating;
import com.polar_moviechart.movieservice.domain.repository.MovieRatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovieRatingCommandServiceTest extends MovieTestConfig {

    @Autowired
    private MovieRatingRepository movieRatingRepository;
    @Autowired
    private MovieRatingCommandService movieRatingCommandService;
    @MockBean
    private UserValidationService userValidationService;

    @BeforeEach
    void setUp() {
        BDDMockito.willDoNothing().given(userValidationService).validateUserExists(1L);
    }

    @DisplayName("기존 평점이 없을 때 영화 평점을 매길 수 있다.")
    @Test
    void updateRatingTest_whenRatingDoesNotExists() {
        // given
        Long userId = 1L;
        int movieCode = 1;
        double ratingValue = 5.5;
        UpdateRatingRequest updateRatingRequest = new UpdateRatingRequest(ratingValue);

        // when
        movieRatingCommandService.updateRating(movieCode, userId, updateRatingRequest);
        // then
        Optional<MovieRating> savedRating = movieRatingRepository.findByCodeAndUserId(movieCode, userId);
        assertTrue(savedRating.isPresent());
        assertEquals(ratingValue, savedRating.get().getRating());
    }

    @DisplayName("기존 평점이 있을 때 새로운 평점을 매기면 기존의 평점이 업데이트 된다.")
    @Test
    void updateRatingTest_whenRatingExists() {
        // given
        Long userId = 1L;
        int movieCode = 11;
        double existingRatingValue = 5.5;

        MovieRating existingMovieRating = new MovieRating(userId, movieCode, existingRatingValue, LocalDateTime.now(), LocalDateTime.now());
        movieRatingRepository.save(existingMovieRating);

        double newRatingValue = 8.0;
        UpdateRatingRequest updateRatingRequest = new UpdateRatingRequest(newRatingValue);
        // when
        movieRatingCommandService.updateRating(movieCode, userId, updateRatingRequest);
        MovieRating updatedMovieRating = movieRatingRepository
                .findByCodeAndUserId(movieCode, userId).get();
        // then
        assertEquals(existingMovieRating.getId(), updatedMovieRating.getId());
        assertEquals(newRatingValue, updatedMovieRating.getRating());
    }
}
