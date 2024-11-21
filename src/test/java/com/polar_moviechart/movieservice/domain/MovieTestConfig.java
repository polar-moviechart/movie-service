package com.polar_moviechart.movieservice.domain;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.repository.MovieRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class MovieTestConfig {

    @Autowired
    protected MovieRepository movieRepository;

    private static List<Movie> movies = new ArrayList<>();
    private static List<Integer> movieCodes;

    protected static void initMovieCodes(List<Integer> codes) {
        movieCodes = codes;
    }

    @BeforeAll
    void setUpMovies() {
        int year = 2004;
        for (Integer movieCode : movieCodes) {
            LocalDate releaseDate = LocalDate.of(year, 1, 1);
            Movie movie = movieRepository.save(
                    Movie.builder()
                            .code(movieCode)
                            .title("title" + movieCode)
                            .details("details" + movieCode)
                            .releaseDate(releaseDate)
                            .productionYear(year)
                            .synopsys("synopsys" + movieCode)
                            .build());
            this.movies.add(movie);
        }
    }

    protected List<Movie> getMovies() {
        return new ArrayList<>(this.movies);
    }
}
