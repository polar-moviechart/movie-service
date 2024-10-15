package com.polar_moviechart.movieservice.domain.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MovieDirectorDto {
    private final int code;
    private final String name;
}
