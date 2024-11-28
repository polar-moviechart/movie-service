package com.polar_moviechart.movieservice.domain.dto;

import java.time.LocalDateTime;

public class ReviewResponse {
    private Long reviewId;
    private String content;
    private String reviewerName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}