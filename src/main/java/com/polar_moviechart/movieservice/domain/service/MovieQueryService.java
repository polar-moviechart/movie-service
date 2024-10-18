package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MovieQueryService {

    private final MovieRepository movieRepository;

    public MovieDetailsDto getMovie(int code) {
        return fetchMovie(code).toDto();
    }
    private Movie fetchMovie(int code) {
        return movieRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("영화를 찾을 수 없습니다."));
    }
}
