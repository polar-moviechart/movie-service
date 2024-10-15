package com.polar_moviechart.movieservice.domain.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class MovieDto {
    private final int code;
    private final String title;
    private final String details;
    private final LocalDate releaseDate;
    private final Integer productionYear;
    private final String synopsys;
}
