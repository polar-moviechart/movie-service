package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.controller.UpdateRatingRequest;
import com.polar_moviechart.movieservice.domain.entity.MovieRating;
import com.polar_moviechart.movieservice.domain.repository.MovieRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieRatingCommandService {
    private final MovieRatingRepository movieRatingRepository;
    private final MovieQueryService movieQueryService;
    private final UserValidationService userValidationService;

    @Transactional
    public double updateRating(Integer code, Long userId, UpdateRatingRequest updateRatingRequest) {
        double ratingValue = updateRatingRequest.getRating();

        userValidationService.validateUserExists(userId);
        Optional<MovieRating> movieRatingOptional = movieRatingRepository.findByCodeAndUserId(code, userId);

        if (movieRatingOptional.isPresent()) {
            MovieRating movieRating = movieRatingOptional.get();
            movieRating.setRating(ratingValue);
        } else {
            movieQueryService.validateExists(code);
            MovieRating movieRating = new MovieRating(userId, code, ratingValue);
            movieRatingRepository.save(movieRating);
        }
        return ratingValue;
    }
}
