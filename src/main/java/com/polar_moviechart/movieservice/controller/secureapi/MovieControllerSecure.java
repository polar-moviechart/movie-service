package com.polar_moviechart.movieservice.controller.secureapi;

import com.polar_moviechart.movieservice.domain.service.dtos.MovieDto;
import com.polar_moviechart.movieservice.domain.service.movie.MovieCommandService;
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

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/secure/api/v1/movies")
public class MovieControllerSecure {
    private final MovieCommandService movieCommandService;
    private final MovieQueryService movieQueryService;
    private final UserServiceHandler userServiceHandler;

    @GetMapping("/{code}/rating")
    public ResponseEntity<CustomResponse<Double>> getMovieRating(HttpServletRequest request,
                                       @PathVariable(name = "code") int code) {
        Long userId = (Long) request.getAttribute("userId");
        userServiceHandler.validateUserExists(userId);
        Double movieRating = movieQueryService.getUserMovieRating(code, userId);

        return ResponseEntity.ok(new CustomResponse<>(movieRating));
    }

    @GetMapping("/likes")
    public ResponseEntity<CustomResponse<List<MovieDto>>> getLikedMovies(
            HttpServletRequest request,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Long userId = getUserId(request);
        PageRequest pageable = PageRequest.of(page, size);
        List<MovieLikesRes> userMovieLikes = userServiceHandler.getUserMovieLikes(null, userId, pageable);

        List<Integer> movieCodes = userMovieLikes.stream().map(MovieLikesRes::getMovieCode).toList();
        List<MovieDto> likedMovies = movieQueryService.getMoviesByCodes(movieCodes);

        return ResponseEntity.ok(new CustomResponse<>(likedMovies));
    }

    private Long getUserId(HttpServletRequest request) {
        return Long.parseLong(request.getHeader("X-User-Id"));
    }
}
