package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.controller.StarRatingReq;
import com.polar_moviechart.movieservice.domain.entity.MovieRating;
import com.polar_moviechart.movieservice.domain.repository.MovieRatingRepository;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
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
    public double updateRating(Integer code, Long userId, StarRatingReq starRatingReq) {
        double ratingValue = starRatingReq.getRating();

        validateUser(userId);
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

    private void validateUser(Long userId) {
        if (!userValidationService.isUserExists(userId)) {
            throw new MovieBusinessException("유저가 존재하지 않습니다.");
        }
    }
}
