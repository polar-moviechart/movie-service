package com.polar_moviechart.movieservice.handler.dtos;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserMoviesLikeReq {
    private Long userId;
    private List<Integer> movieCodes = new ArrayList<>();
    public UserMoviesLikeReq(Long userId, List<Integer> movieCodes) {
        this.userId = userId;
        this.movieCodes = movieCodes;
    }

    public UserMoviesLikeReq(Long userId) {
        this.userId = userId;
    }
}
