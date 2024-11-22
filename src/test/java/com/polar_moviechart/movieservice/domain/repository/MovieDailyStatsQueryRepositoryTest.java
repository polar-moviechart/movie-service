package com.polar_moviechart.movieservice.domain.repository;

import com.polar_moviechart.movieservice.domain.MovieStatsTestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;


class MovieDailyStatsQueryRepositoryTest extends MovieStatsTestConfig {
    @Autowired MovieDailyStatsQueryRepository dailyStatsQueryRepository;
    private List<Integer> movieCodes;
    private LocalDate releaseDate;
    private int stat = 100;
    private int days = 3;

    @BeforeEach
    public void setUp() {
        movieCodes = List.of(1, 2);
        releaseDate = LocalDate.of(2024, 1, 1);
        initMovies(movieCodes, releaseDate);
        initStat(stat, days);
    }

    @DisplayName("getAllDates() 쿼리 테스트")
    @Test
    void getAllDates() {
        // given // when
        List<LocalDate> movieDates = dailyStatsQueryRepository.getAllDates();
        // then
        Assertions.assertThat(movieDates.size()).isEqualTo(days);
    }
}