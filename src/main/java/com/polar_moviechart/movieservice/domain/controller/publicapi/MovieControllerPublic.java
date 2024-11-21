package com.polar_moviechart.movieservice.domain.controller.publicapi;

import com.polar_moviechart.movieservice.domain.enums.StatType;
import com.polar_moviechart.movieservice.domain.service.*;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDailyStatsResponse;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDetailsDto;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDto;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/public/api/v1/movies")
public class MovieControllerPublic {

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
                                                                                 @RequestParam(name = "type") StatType statType) {
        PageRequest pageable = PageRequest.of(0, limit);
        MovieDailyStatsResponse movieDailyStats = movieDailyStatsQueryService.getMovieDailyStats(code, pageable, statType);

        return ResponseEntity.ok(new CustomResponse<>(movieDailyStats));
    }
}
