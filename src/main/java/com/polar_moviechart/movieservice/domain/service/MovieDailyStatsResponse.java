package com.polar_moviechart.movieservice.domain.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MovieDailyStatsResponse {
    private final int code;
    private final List<StatDto> statDtos;
}
