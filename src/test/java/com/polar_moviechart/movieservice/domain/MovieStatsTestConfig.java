package com.polar_moviechart.movieservice.domain;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.entity.MovieDailyStat;
import com.polar_moviechart.movieservice.domain.repository.MovieDailyStatsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class MovieStatsTestConfig extends MovieTestConfig {

    @Autowired
    protected MovieDailyStatsRepository dailyStatsRepository;

    @BeforeEach
    void setUpMovieStats() {
        List<Movie> movies = movieRepository.findAll();
        for (Movie movie : movies) {
            for (int i = 0; i < 10; i++) {
                dailyStatsRepository.save(
                        MovieDailyStat.builder()
                                .ranking(i + 1)
                                .revenue((i + 1) * 100)
                                .date(movie.getReleaseDate().plusDays(i))
                                .audience((i + 1) * 100)
                                .movie(movie)
                                .build());
            }
        }
    }
}
