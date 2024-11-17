package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.BaseTestConfig;
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

@Import(MovieRatingCommandService.class)
@Transactional
class MovieRatingCommandServiceTest extends BaseTestConfig {

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

    @DisplayName("")
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

    @DisplayName("")
    @Test
    void updateRatingTest_whenRatingExists() {
        // given
        Long userId = 1L;
        int movieCode = 11;
        double existingRatingValue = 5.5;
        double newRatingValue = 8.0;
        MovieRating existingMovieRating = new MovieRating(userId, movieCode, existingRatingValue, LocalDateTime.now(), LocalDateTime.now());
        movieRatingRepository.save(existingMovieRating);
        // when
        UpdateRatingRequest updateRatingRequest = new UpdateRatingRequest(newRatingValue);
        movieRatingCommandService.updateRating(movieCode, userId, updateRatingRequest);
        // then
        Optional<MovieRating> updatedMovieRating = movieRatingRepository.findByCodeAndUserId(movieCode, userId);
        assertTrue(updatedMovieRating.isPresent());
        assertEquals(newRatingValue, updatedMovieRating.get().getRating());
    }
}
