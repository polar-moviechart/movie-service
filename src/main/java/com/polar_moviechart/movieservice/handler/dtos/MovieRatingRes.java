package com.polar_moviechart.movieservice.handler.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MovieRatingRes {
    private Integer movieCode;
    private String title;
    private Double movieRating;
    private LocalDateTime createdAt;

    @Builder
    public MovieRatingRes(Integer movieCode, Double movieRating) {
        this.movieCode = movieCode;
        this.movieRating = movieRating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMovieRating(Double movieRating) {
        this.movieRating = movieRating;
    }
}
