package com.polar_moviechart.movieservice.domain.dto;

import lombok.Getter;

@Getter
public class UserActivityInfo {
    private Boolean likeStatus;
    private Double rating;
    private MovieReviewRes review;
}
