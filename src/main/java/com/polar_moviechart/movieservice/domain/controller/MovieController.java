package com.polar_moviechart.movieservice.domain.controller;

import com.polar_moviechart.movieservice.domain.service.DailyMovieRankDto;
import com.polar_moviechart.movieservice.domain.service.MovieDailyStatsQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieDailyStatsQueryService movieDailyStatsQueryService;

    @GetMapping("")
    public DailyMovieRankDto getMovies(
            @RequestParam(defaultValue = "#{T(java.time.LocalDate).now().toString()}") LocalDate targetDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return movieDailyStatsQueryService.getMovieDailyRankInfo(targetDate, pageable);
    }
}
