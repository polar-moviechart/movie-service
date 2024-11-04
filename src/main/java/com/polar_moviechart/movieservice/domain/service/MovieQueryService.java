package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.repository.MovieRepository;
import com.polar_moviechart.movieservice.exception.MovieBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MovieQueryService {

    private final MovieRepository movieRepository;

    public MovieDetailsDto getMovie(int code) {
        return fetchMovie(code).toDto();
    }

    public void validateExists(int code) {
        if (!isExists(code)) {
            throw new MovieBusinessException("영화가 존재하지 않습니다.");
        }
    }
    public boolean isExists(int code) {
        return movieRepository.existsByCode(code);
    }
    private Movie fetchMovie(int code) {
        return movieRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("영화를 찾을 수 없습니다."));
    }
}
