package com.polar_moviechart.movieservice.domain.controller.secureapi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateRatingRequest {
    private Double rating;
}
