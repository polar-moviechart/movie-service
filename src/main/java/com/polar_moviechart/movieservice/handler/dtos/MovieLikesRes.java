package com.polar_moviechart.movieservice.handler.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MovieLikesRes {

    private Integer movieCode;
    private boolean likeStatus;

    @Builder
    public MovieLikesRes(Integer movieCode, Boolean likeStatus) {
        this.movieCode = movieCode;
        this.likeStatus = likeStatus;
    }
}
