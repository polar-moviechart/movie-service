package com.polar_moviechart.movieservice.controller.publicapi;

import com.polar_moviechart.movieservice.domain.dto.ReviewResponse;
import com.polar_moviechart.movieservice.utils.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/public/api/v1/movies")
public abstract class MovieDataProxyController {

    @GetMapping("/{code}/likes")
    public abstract ResponseEntity<CustomResponse<Integer>> getLikes(@PathVariable(name = "code") int code);

    @GetMapping("/{code}/reviews")
    public abstract ResponseEntity<CustomResponse<List<ReviewResponse>>> getReviews(@PathVariable(name = "code") int code);

    @GetMapping("/{code}/ratings")
    public abstract ResponseEntity<CustomResponse<Double>> getRatings(@PathVariable(name = "code") int code);
}
