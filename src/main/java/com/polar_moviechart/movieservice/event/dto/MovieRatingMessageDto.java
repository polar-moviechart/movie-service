package com.polar_moviechart.movieservice.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MovieRatingMessageDto {
    private Long userId;
    private Integer code;
    private Double value;
    private Boolean isNew;
    private Double oldValue;
    private UserActivityType type;

    @Builder
    public MovieRatingMessageDto(Long userId, Integer code, Double value, UserActivityType type) {
        this.userId = userId;
        this.code = code;
        this.value = value;
        this.type = type;
    }
}

