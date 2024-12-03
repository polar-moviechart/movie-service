package com.polar_moviechart.movieservice.domain.service.movie;

import com.polar_moviechart.movieservice.controller.secureapi.UpdateRatingRequest;
import com.polar_moviechart.movieservice.domain.entity.MovieRating;
import com.polar_moviechart.movieservice.event.dto.MovieLikeMessageDto;
import com.polar_moviechart.movieservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieCommandService {
    private final MovieQueryService movieQueryService;
    private final MovieRepository movieRepository;
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

    @Transactional
    public void updateLike(MovieLikeMessageDto message) {
        movieRepository.findByCode(message.getCode())
                .ifPresent(movie -> {
                    movie.addLikeCount(message.getValue() ? +1 : -1);
                });
    }
}
