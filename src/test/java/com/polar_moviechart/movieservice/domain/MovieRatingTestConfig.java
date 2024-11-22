package com.polar_moviechart.movieservice.domain;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.entity.MovieRating;
import com.polar_moviechart.movieservice.domain.repository.MovieRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class MovieRatingTestConfig extends MovieTestConfig {

    @Autowired
    protected MovieRatingRepository movieRatingRepository;

    private List<Long> userIds;
    private List<Double> ratingValues;

    protected void initRating(List<Double> ratingValues, List<Long> userIds) {
        this.ratingValues = ratingValues;
        this.userIds = userIds;
        setUpMovieRatings();
    }

    private void setUpMovieRatings() {
        for (Movie movie : getMovies()) {

            for (int idx = 0; idx < ratingValues.size(); idx++) {
                movieRatingRepository.save(
                        MovieRating.builder()
                                .userId(userIds.get(idx))
                                .movieCode(movie.getCode())
                                .rating(ratingValues.get(idx))
                                .build());
            }
        }
    }
}
