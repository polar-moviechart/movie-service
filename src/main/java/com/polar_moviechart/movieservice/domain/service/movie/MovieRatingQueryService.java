package com.polar_moviechart.movieservice.domain.service.movie;

import com.polar_moviechart.movieservice.domain.entity.MovieRating;
import com.polar_moviechart.movieservice.repository.MovieRatingRepository;
import com.polar_moviechart.movieservice.exception.ErrorCode;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieRatingQueryService {
    private final MovieRatingRepository movieRatingRepository;

    public Double getUserMovieRating(int code, Long userId) {
        return movieRatingRepository.findByCodeAndUserId(code, userId)
                .map(MovieRating::getRating)
                .orElseThrow(() -> new MovieBusinessException(ErrorCode.RATING_NOT_EXISTS));
    }

    public Optional<MovieRating> findByCodeAndUserId(Integer code, Long userId) {
        return movieRatingRepository.findByCodeAndUserId(code, userId);
    }
}
