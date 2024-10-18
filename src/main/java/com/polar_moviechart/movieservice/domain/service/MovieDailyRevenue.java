package com.polar_moviechart.movieservice.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class MovieDailyRevenue implements MovieDailyStat {
    private final LocalDate date;
    private final int revenue;
}
