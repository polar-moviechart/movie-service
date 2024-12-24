package com.polar_moviechart.movieservice.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MovieStatDatesRes {
    private LocalDate startDate;
    private LocalDate endDate;

    public MovieStatDatesRes(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
