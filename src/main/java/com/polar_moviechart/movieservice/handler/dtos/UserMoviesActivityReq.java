package com.polar_moviechart.movieservice.handler.dtos;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserMoviesActivityReq {
    private Long userId;
    private List<Integer> movieCodes = new ArrayList<>();
    public UserMoviesActivityReq(Long userId, List<Integer> movieCodes) {
        this.userId = userId;
        this.movieCodes = movieCodes;
    }

    public UserMoviesActivityReq(Long userId) {
        this.userId = userId;
    }
}
