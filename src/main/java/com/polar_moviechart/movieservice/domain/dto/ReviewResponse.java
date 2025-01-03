package com.polar_moviechart.movieservice.domain.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponse {
    private Long reviewId;
    private String content;
    private String reviewerName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}