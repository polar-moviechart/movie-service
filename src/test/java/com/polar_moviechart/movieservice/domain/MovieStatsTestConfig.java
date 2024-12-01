package com.polar_moviechart.movieservice.domain;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.entity.MovieDailyStat;
import com.polar_moviechart.movieservice.repository.MovieDailyStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MovieStatsTestConfig extends MovieTestConfig {

    @Autowired
    protected MovieDailyStatsRepository dailyStatsRepository;

    private Integer stat;
    private Integer days;

    protected void initStat(Integer stat, int days) {
        this.stat = stat;
        this.days = days;
        setUpMovieStats();
    }

    private void setUpMovieStats() {
        for (Movie movie : getMovies()) {

            for (int day = 0; day < days; day++) {
                dailyStatsRepository.save(
                        MovieDailyStat.builder()
                                .ranking(stat)
                                .revenue(stat * 100)
                                .date(movie.getReleaseDate().plusDays(day))
                                .audience(stat * 100)
                                .movie(movie)
                                .build());
            }
        }
    }
}
