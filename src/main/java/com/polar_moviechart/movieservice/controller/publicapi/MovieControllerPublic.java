package com.polar_moviechart.movieservice.controller.publicapi;

import com.polar_moviechart.movieservice.domain.enums.StatType;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDailyStatsResponse;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDetailsDto;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDto;
import com.polar_moviechart.movieservice.domain.service.movie.MovieQueryService;
import com.polar_moviechart.movieservice.handler.UserServiceHandler;
import com.polar_moviechart.movieservice.handler.dtos.MovieLikesRes;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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
            HttpServletRequest servletRequest,
            @RequestParam(required = false) LocalDate targetDateReq,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<MovieDto> movieDtos = movieQueryService.getMovies(targetDateReq, pageRequest);

        Long userId = getUserId(servletRequest);
        if (userId != null) {
            List<Integer> movieCodes = movieDtos.stream().map(MovieDto::getCode).toList();
            setLike(movieDtos, userId, movieCodes);
        }
        return ResponseEntity.ok(new CustomResponse<>(movieDtos));
    }

    private void setLike(List<MovieDto> movieDtos, Long userId, List<Integer> movieCodes) {
        List<MovieLikesRes> userMovieLikes = userServiceHandler.getUserMovieLikes(movieCodes, userId);
        for (MovieLikesRes movieLike : userMovieLikes) {
            movieDtos.stream()
                    .filter(movieDto -> movieDto.getCode() == movieLike.getMovieCode())
                    .findFirst()
                    .ifPresent(movieDto -> movieDto.setIsLike(movieLike.isLikeStatus()));
        }
//        userServiceHandler.getUserMovieLikes(movieCodes, userId)
//                .forEach(movieLike -> movieDtos.stream()
//                        .filter(movieDto -> movieDto.getCode() == movieLike.getMovieCode())
//                        .findFirst()
//                        .ifPresent(movieDto -> movieDto.setIsLike(movieLike.isLike())));
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
        String userId = request.getHeader("X-User-Id");
        if (userId != null) {
            return Long.parseLong(userId);
        }
        return null;
    }
}
