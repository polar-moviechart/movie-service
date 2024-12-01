package com.polar_moviechart.movieservice.domain.service.movie;

import com.polar_moviechart.movieservice.controller.secureapi.UpdateRatingRequest;
import com.polar_moviechart.movieservice.domain.entity.MovieRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieCommandService {
    private final MovieQueryService movieQueryService;
    private final MovieRatingCommandService movieRatingCommandService;
    private final MovieRatingQueryService movieRatingQueryService;

    @Transactional
    public double updateRating(Integer code, Long userId, UpdateRatingRequest updateRatingRequest) {
        double ratingValue = updateRatingRequest.getRating();
        Optional<MovieRating> movieRatingOptional = movieRatingQueryService.findByCodeAndUserId(code, userId);

        if (movieRatingOptional.isPresent()) {
            MovieRating movieRating = movieRatingOptional.get();
            movieRating.setRating(ratingValue);
        } else {
            movieQueryService.validateExists(code);
            movieRatingCommandService.updateRating(code, userId, ratingValue);
        }
        return ratingValue;
    }
}
