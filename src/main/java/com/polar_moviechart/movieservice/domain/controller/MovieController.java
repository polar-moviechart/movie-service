package com.polar_moviechart.movieservice.domain.controller;

import com.polar_moviechart.movieservice.domain.enums.StatField;
import com.polar_moviechart.movieservice.domain.service.*;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CustomResponse<List<MovieDto>>> getMovies(
            @RequestParam(defaultValue = "#{T(java.time.LocalDate).now().toString()}") LocalDate targetDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        List<MovieDto> movieDtos = movieDailyStatsQueryService.getMovieDailyRankInfo(targetDate, pageable);

        return ResponseEntity.ok(new CustomResponse<>(movieDtos));
    }

    @GetMapping("/{code}")
    public ResponseEntity<CustomResponse<MovieDetailsDto>> getMovie(@PathVariable(name = "code") int code) {
        MovieDetailsDto movieDetailsDto = movieQueryService.getMovie(code);
        return ResponseEntity.ok(new CustomResponse<>(movieDetailsDto));
    }

    @GetMapping("/{code}/stats")
    public ResponseEntity<CustomResponse<MovieDailyStatsResponse>> getMovieStats(@PathVariable(name = "code") int code,
                                                 @RequestParam(name = "limit") int limit,
                                                 @RequestParam(name = "field") StatField statField) {
        PageRequest pageable = PageRequest.of(0, limit);
        MovieDailyStatsResponse movieDailyStats = movieDailyStatsQueryService.getMovieDailyStats(code, pageable, statField);

        return ResponseEntity.ok(new CustomResponse<>(movieDailyStats));
    }
}
