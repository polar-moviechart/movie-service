package com.polar_moviechart.movieservice.domain.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class DailyMovieRankDto {
    private final LocalDate date;

    private final List<MovieDailyRankDto> rankDtos;
}
