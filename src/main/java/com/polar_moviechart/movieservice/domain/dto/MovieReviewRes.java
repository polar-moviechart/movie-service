package com.polar_moviechart.movieservice.domain.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MovieReviewRes {
    private Long id;
    private Long userId;
    private String nickname;
    private Integer code;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
