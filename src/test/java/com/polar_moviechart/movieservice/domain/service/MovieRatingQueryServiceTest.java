package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.MovieTestConfig;
import com.polar_moviechart.movieservice.domain.entity.MovieRating;
import com.polar_moviechart.movieservice.domain.repository.MovieRatingRepository;
import com.polar_moviechart.movieservice.exception.ErrorInfo;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieRatingQueryServiceTest {
    @Mock
    private UserValidationService userValidationService;
    @InjectMocks
    private MovieRatingQueryService ratingQueryService;
    @Mock
    private MovieRatingRepository ratingRepository;

    private final double expectedRating = 4.5;
    private final int movieCode = 1;
    private final long userId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화
    }

    @DisplayName("유저, 영화가 존재하면 영화 평점을 매길 수 있다.")
    @Test
    void validateUserExists_userExists_movieExists() {
        // given
        MovieRating movieRating = new MovieRating(userId, movieCode, expectedRating);

        doNothing()
                .when(userValidationService)
                .validateUserExists(userId);
        when(ratingRepository.findByCodeAndUserId(movieCode, userId))
                .thenReturn(Optional.of(movieRating));
        when(ratingRepository.findByCodeAndUserId(movieCode, userId))
                .thenReturn(Optional.of(movieRating));
        // when
        Double rating = ratingQueryService.getUserMovieRating(movieCode, userId);
        // then
        assertEquals(expectedRating, rating);
    }

    @DisplayName("영화가 존재하지 않으면 예외가 발생한다.")
    @Test
    void validateUserExists_userExists_movieNotExists() {
        // given
        doNothing().when(userValidationService).validateUserExists(userId);
        when(ratingRepository.findByCodeAndUserId(movieCode, userId)).thenReturn(Optional.empty());
        // when then
        MovieBusinessException exception = assertThrows(MovieBusinessException.class,
                () -> ratingQueryService.getUserMovieRating(movieCode, userId)
        );
        assertEquals(ErrorInfo.RATING_NOT_EXISTS.getCode(), exception.getCode());
    }

    @DisplayName("유저가 존재하지 않으면 예외가 발생한다.")
    @Test
    void validateUserExists_userNotExists() {
        // given
        BDDMockito.doThrow(new MovieBusinessException(ErrorInfo.USER_NOT_EXISTS))
                        .when(userValidationService)
                        .validateUserExists(userId);
        // when then
        MovieBusinessException exception = assertThrows(MovieBusinessException.class,
                () -> ratingQueryService.getUserMovieRating(movieCode, userId)
        );
        assertEquals("U201", exception.getCode());
    }
}
