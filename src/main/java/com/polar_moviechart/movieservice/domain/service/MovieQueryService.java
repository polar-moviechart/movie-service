package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.repository.MovieRepository;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDetailsDto;
import com.polar_moviechart.movieservice.exception.ErrorCode;
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
            throw new MovieBusinessException(ErrorCode.MOVIE_DOESNT_EXISTS);
        }
    }
    private boolean isExists(int code) {
        return movieRepository.existsByCode(code);
    }
    private Movie fetchMovie(int code) {
        return movieRepository.findByCode(code)
                .orElseThrow(() -> new MovieBusinessException(ErrorCode.MOVIE_DOESNT_EXISTS));
    }
}
