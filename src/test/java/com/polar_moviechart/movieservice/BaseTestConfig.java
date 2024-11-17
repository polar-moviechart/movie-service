package com.polar_moviechart.movieservice;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.entity.MovieDailyStat;
import com.polar_moviechart.movieservice.domain.repository.MovieDailyStatsRepository;
import com.polar_moviechart.movieservice.domain.repository.MovieRatingRepository;
import com.polar_moviechart.movieservice.domain.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseTestConfig {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieRatingRepository movieRatingRepository;

    @Autowired
    private MovieDailyStatsRepository dailyStatsRepository;

    @BeforeEach
    void setUpData() {
        List<Integer> movieCodes = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int year = 2004;
        for (int movieCode : movieCodes) {
            LocalDate releaseDate = LocalDate.of(year, 1, 1);
            Movie savedMovie = movieRepository.save(
                    Movie.builder()
                            .code(movieCode)
                            .title("title" + movieCode)
                            .details("details" + movieCode)
                            .releaseDate(releaseDate)
                            .productionYear(year)
                            .synopsys("synopsys" + movieCode)
                            .build());

            // MovieDailyStat
            for (int i = 1; i <= 10; i++) {
                dailyStatsRepository.save(
                        MovieDailyStat.builder()
                        .ranking(i)
                        .revenue(i * 100)
                        .date(releaseDate.plusDays(i))
                        .audience(i * 100)
                        .movie(savedMovie)
                        .build());
            }
        }
    }
}
