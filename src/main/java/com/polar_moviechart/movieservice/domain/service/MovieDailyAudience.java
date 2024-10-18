package com.polar_moviechart.movieservice.domain.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class MovieDailyAudience implements MovieDailyStat {
    private final LocalDate date;
    private final int audience;
}
