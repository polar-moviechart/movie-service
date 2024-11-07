package com.polar_moviechart.movieservice.domain.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateRatingRequest {
    private final double rating;
}
