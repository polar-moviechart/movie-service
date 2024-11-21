package com.polar_moviechart.movieservice.domain;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.entity.MovieDailyStat;
import com.polar_moviechart.movieservice.domain.repository.MovieDailyStatsRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MovieStatsTestConfig extends MovieTestConfig {

    @Autowired
    protected MovieDailyStatsRepository dailyStatsRepository;

    private static Integer statCnt;
    private static Integer days;

    protected static void initStat(Integer statCnt, int days) {
        statCnt = statCnt;
        days = days;
    }



    @BeforeAll
    void setUpMovieStats() {
        for (Movie movie : getMovies()) {

            for (int day = 0; day < days; day++) {

                for (int stat = 1; stat <= statCnt; stat++) {
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
}
