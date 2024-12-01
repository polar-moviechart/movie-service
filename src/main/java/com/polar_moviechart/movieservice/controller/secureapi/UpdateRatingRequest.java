package com.polar_moviechart.movieservice.controller.secureapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRatingRequest {
    private Double rating;
}
