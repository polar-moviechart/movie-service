package com.polar_moviechart.movieservice.controller.internalapi;

import com.polar_moviechart.movieservice.domain.service.movie.MovieQueryService;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/api/v1/movies")
public class MovieControllerInternal {
    private final MovieQueryService movieQueryService;

    @GetMapping("/{movieCode}")
    public ResponseEntity<CustomResponse<Boolean>> userExists(@PathVariable("movieCode") Integer movieCode) {
        boolean isExists = movieQueryService.isExists(movieCode);
        return ResponseEntity.ok(new CustomResponse(isExists));
    }
}
