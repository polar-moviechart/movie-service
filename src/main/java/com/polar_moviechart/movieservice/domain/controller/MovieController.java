package com.polar_moviechart.movieservice.domain.controller;

import com.polar_moviechart.movieservice.domain.enums.StatField;
import com.polar_moviechart.movieservice.domain.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieDailyStatsQueryService movieDailyStatsQueryService;
    private final MovieQueryService movieQueryService;

    @GetMapping("")
    public List<MovieDto> getMovies(
            @RequestParam(defaultValue = "#{T(java.time.LocalDate).now().toString()}") LocalDate targetDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return movieDailyStatsQueryService.getMovieDailyRankInfo(targetDate, pageable);
    }

    @GetMapping("/{code}")
    public MovieDetailsDto getMovie(@PathVariable(name = "code") int code) {
        return movieQueryService.getMovie(code);
    }

    @GetMapping("/{code}/stats")
    public MovieDailyStatsResponse getMovieStats(@PathVariable(name = "code") int code,
                                                 @RequestParam(name = "limit") int limit,
                                                 @RequestParam(name = "field") StatField statField) {
        PageRequest pageable = PageRequest.of(0, limit);
        return movieDailyStatsQueryService.getMovieDailyStats(code, pageable, statField);
    }
}
