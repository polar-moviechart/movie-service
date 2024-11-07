package com.polar_moviechart.movieservice.domain.controller.secureapi;

import com.polar_moviechart.movieservice.domain.service.MovieRatingCommandService;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/secure/api/v1/movies")
public class MovieControllerSecure {

    private final MovieRatingCommandService movieRatingCommandService;

    @PostMapping("/{code}/rating")
    public ResponseEntity updateRating(HttpServletRequest request,
                                       @PathVariable(name = "code") int code,
                                       @RequestBody UpdateRatingRequest updateRatingRequest) {
        Long userId = (Long) request.getAttribute("userId");
        double ratingValue = movieRatingCommandService.updateRating(code, userId, updateRatingRequest);

        return ResponseEntity.ok(new CustomResponse<>(ratingValue));
    }
}
