package com.polar_moviechart.movieservice.domain;

import com.polar_moviechart.movieservice.domain.entity.Director;
import com.polar_moviechart.movieservice.domain.entity.Movie;
import com.polar_moviechart.movieservice.domain.entity.MovieDirector;
import com.polar_moviechart.movieservice.domain.repository.DirectorRepository;
import com.polar_moviechart.movieservice.domain.repository.MovieDirectorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

public class DirectorTestConfig extends MovieTestConfig {

    @Autowired private DirectorRepository directorRepository;
    @Autowired private MovieDirectorRepository movieDirectorRepository;

    @BeforeAll
    void setUpDirectors() {
        for (Movie movie : getMovies()) {
            int movieCode = movie.getCode();
            Director director = directorRepository.save(Director.builder()
                    .name("감독" + movieCode)
                    .code(movieCode)
                    .build());

            movieDirectorRepository.save(
                    MovieDirector.builder()
                            .movie(movie)
                            .director(director)
                            .build());
        }
    }
}
