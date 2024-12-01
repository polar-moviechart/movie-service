package com.polar_moviechart.movieservice.controller.secureapi;

import com.polar_moviechart.movieservice.domain.service.movie.MovieCommandService;
import com.polar_moviechart.movieservice.domain.service.movie.MovieQueryService;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/secure/api/v1/movies")
public class MovieControllerSecure {
    private final MovieCommandService movieCommandService;
    private final MovieQueryService movieQueryService;

    @GetMapping("/{code}/rating")
    public ResponseEntity<CustomResponse<Double>> getMovieRating(HttpServletRequest request,
                                       @PathVariable(name = "code") int code) {
        Long userId = (Long) request.getAttribute("userId");
        Double movieRating = movieQueryService.getUserMovieRating(code, userId);

        return ResponseEntity.ok(new CustomResponse<>(movieRating));
    }

    @PostMapping("/{code}/rating")
    public ResponseEntity<CustomResponse<Double>> updateRating(HttpServletRequest request,
                                       @PathVariable(name = "code") int code,
                                       @RequestBody UpdateRatingRequest updateRatingRequest) {
        String userIdString = request.getHeader("X-User-Id");
        Long userId = Long.valueOf(userIdString);
        double ratingValue = movieCommandService.updateRating(code, userId, updateRatingRequest);

        return ResponseEntity.ok(new CustomResponse<>(ratingValue));
    }
}
