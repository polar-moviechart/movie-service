package com.polar_moviechart.movieservice.controller.internalapi;

import com.polar_moviechart.movieservice.domain.service.dtos.MovieDto;
import com.polar_moviechart.movieservice.domain.service.movie.MovieQueryService;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/codes")
    public ResponseEntity<CustomResponse<List<MovieDto>>> getMoviesByCodes(@RequestBody List<Integer> codes) {
        List<MovieDto> movieDtos = movieQueryService.getMoviesByCodes(codes);
        return ResponseEntity.ok(new CustomResponse<>(movieDtos));
    }
}
