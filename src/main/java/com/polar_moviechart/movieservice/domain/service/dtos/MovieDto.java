package com.polar_moviechart.movieservice.domain.service.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class MovieDto {
    private final int code;
    private final int ranking;
    private final String title;
    private final List<String> poster;
    private final String details;
    private final LocalDate releaseDate;
    private final Integer productionYear;

    private final List<MovieDirectorDto> movieDirectorDtos;
    private final List<MovieLeadactorDto> movieLeadactorDtos;
}
