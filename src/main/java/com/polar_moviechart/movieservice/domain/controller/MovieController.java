package com.polar_moviechart.movieservice.domain.controller;

import com.polar_moviechart.movieservice.domain.service.MovieDto;
import com.polar_moviechart.movieservice.domain.service.MovieQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieQueryService movieQueryService;

    @GetMapping("")
    public List<MovieDto> getMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return movieQueryService.getMovies(pageable);
    }
}
