package com.polar_moviechart.movieservice.domain.service.movie;

import com.polar_moviechart.movieservice.domain.entity.MovieRating;
import com.polar_moviechart.movieservice.domain.repository.MovieRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieRatingCommandService {
    private final MovieRatingRepository movieRatingRepository;

    @Transactional
    public double updateRating(Integer code, Long userId, Double ratingValue) {
        MovieRating movieRating = new MovieRating(userId, code, ratingValue);
        movieRatingRepository.save(movieRating);
        return ratingValue;
    }
}
