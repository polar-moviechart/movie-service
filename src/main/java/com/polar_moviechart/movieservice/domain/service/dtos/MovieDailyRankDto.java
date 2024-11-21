package com.polar_moviechart.movieservice.domain.service.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MovieDailyRankDto {
    private final int ranking;

    private final MovieDto movieDto;
}
