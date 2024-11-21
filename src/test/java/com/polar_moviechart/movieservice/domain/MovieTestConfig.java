package com.polar_moviechart.movieservice.domain;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public abstract class MovieTestConfig {

    @Autowired
    protected MovieRepository movieRepository;

    @BeforeEach
    void setUpMovies() {
        List<Integer> movieCodes = List.of(1, 2, 3, 4, 5);
        int year = 2004;
        for (int movieCode : movieCodes) {
            LocalDate releaseDate = LocalDate.of(year, 1, 1);
            movieRepository.save(
                    Movie.builder()
                            .code(movieCode)
                            .title("title" + movieCode)
                            .details("details" + movieCode)
                            .releaseDate(releaseDate)
                            .productionYear(year)
                            .synopsys("synopsys" + movieCode)
                            .build());
        }
    }
}