package com.polar_moviechart.movieservice.domain.service.movie;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.event.dto.MovieLikeMessageDto;
import com.polar_moviechart.movieservice.event.dto.MovieRatingMessageDto;
import com.polar_moviechart.movieservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieCommandService {
    private final MovieQueryService movieQueryService;
    private final MovieRepository movieRepository;

    @Transactional
    public void updateLike(MovieLikeMessageDto message) {
        movieRepository.findByCode(message.getCode())
                .ifPresent(movie -> {
                    movie.addLikeCount(message.getValue() ? +1 : -1);
                });
    }

    @Transactional
    public void updateRating(MovieRatingMessageDto message) {
        Movie movie = movieQueryService.fetchMovie(message.getCode());
        if (message.getIsNew()) {
            movie.addRating(message.getValue());
        } else {
            movie.updateRating(message.getValue(), message.getOldValue());
        }
    }
}
