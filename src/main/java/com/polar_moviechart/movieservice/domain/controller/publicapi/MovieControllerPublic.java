package com.polar_moviechart.movieservice.domain.controller.publicapi;

import com.polar_moviechart.movieservice.domain.dto.ReviewResponse;
import com.polar_moviechart.movieservice.domain.enums.StatType;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDailyStatsResponse;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDetailsDto;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDto;
import com.polar_moviechart.movieservice.domain.service.movie.MovieQueryService;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/public/api/v1/movies")
public class MovieControllerPublic extends MovieDataProxyController {
    private final MovieQueryService movieQueryService;

    @GetMapping("")
    public ResponseEntity<CustomResponse<List<MovieDto>>> getMovies(
            @RequestParam(required = false) LocalDate targetDateReq,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<MovieDto> movieDtos = movieQueryService.getMovies(targetDateReq, page, size);
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
        MovieDailyStatsResponse movieDailyStats = movieQueryService.getMovieStats(code, limit, statType);
        return ResponseEntity.ok(new CustomResponse<>(movieDailyStats));
    }

    @GetMapping("/dates")
    public ResponseEntity<CustomResponse<List<LocalDate>>> getDates() {
        List<LocalDate> statDates = movieQueryService.getStatDates();
        return ResponseEntity.ok(new CustomResponse<>(statDates));
    }

    @Override
    public ResponseEntity<CustomResponse<Integer>> getLikes(int code) {
        return null;
    }

    @Override
    public ResponseEntity<CustomResponse<List<ReviewResponse>>> getReviews(int code) {
        return null;
    }

    @Override
    public ResponseEntity<CustomResponse<Double>> getRatings(int code) {
        return null;
    }
}
