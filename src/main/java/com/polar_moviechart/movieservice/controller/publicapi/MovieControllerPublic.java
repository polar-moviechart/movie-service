package com.polar_moviechart.movieservice.controller.publicapi;

import com.polar_moviechart.movieservice.domain.enums.StatType;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDailyStatsResponse;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDetailsDto;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDto;
import com.polar_moviechart.movieservice.domain.service.movie.MovieQueryService;
import com.polar_moviechart.movieservice.handler.UserServiceHandler;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/public/api/v1/movies")
public class MovieControllerPublic {
    private final MovieQueryService movieQueryService;
    private final UserServiceHandler userServiceHandler;

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
    public ResponseEntity<CustomResponse<MovieDetailsDto>> getMovie(
            HttpServletRequest servletRequest,
            @PathVariable(name = "code") int code) {
        MovieDetailsDto movieDetailsDto = movieQueryService.getMovie(code);

        Double userMovieRating = Optional.ofNullable(getUserId(servletRequest))
                .map(userId -> userServiceHandler.getUserMovieRating(userId, code)).get();
        movieDetailsDto.setUserMovieRating(userMovieRating);

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

    private Long getUserId(HttpServletRequest request) {
        return Long.parseLong(request.getHeader("X-User-Id"));
    }
}
