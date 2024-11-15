package com.polar_moviechart.movieservice.domain.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class StatDto {
    private final LocalDate date;
    private final Integer value;
}
