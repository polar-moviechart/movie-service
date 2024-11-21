package com.polar_moviechart.movieservice.domain.service.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MovieLeadactorDto {
    private final Integer code;
    private final String name;
}
