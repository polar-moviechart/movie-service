package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieQueryService {
    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<MovieDto> getMovies(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAll(pageable);
        return movies.getContent().stream()
                .map(movie -> movie.toDto())
                .toList();
    }
}
