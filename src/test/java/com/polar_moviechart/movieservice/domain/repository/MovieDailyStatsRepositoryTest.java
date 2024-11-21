package com.polar_moviechart.movieservice.domain.repository;

import com.polar_moviechart.movieservice.domain.MovieStatsTestConfig;
import com.polar_moviechart.movieservice.domain.entity.MovieDailyStat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MovieDailyStatsRepositoryTest extends MovieStatsTestConfig {

    private final static List<Integer> movieCodes = List.of(1);
    private final static LocalDate releaseDate = LocalDate.of(2004, 1, 1);
    private final static int statCnt = 1;
    private final static int days = 2;

    @BeforeAll
    void setUp() {
        initMovies(movieCodes, releaseDate);
        initStat(statCnt, days);
    }

    @DisplayName("findAllByDate() 쿼리 테스트")
    @Test
    void findAllByDate() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 10);
        // when
        Page<MovieDailyStat> movieDailyStatsPage = dailyStatsRepository.findAllByDate(releaseDate, pageRequest);
        // then
        assertThat(movieDailyStatsPage.getContent().size()).isEqualTo(statCnt);
    }

    @DisplayName("findByMovieCodeOrderByDateDesc() 쿼리 테스트")
    @Test
    void findByMovieCodeOrderByDateDesc() {
        // given
        PageRequest pageRequest = PageRequest.of(0, statCnt);
        // when
        List<MovieDailyStat> dailyStats = dailyStatsRepository
                .findByMovieCodeOrderByDateDesc(movieCodes.get(0), pageRequest);
        // then
        Assertions.assertThat(dailyStats.size()).isEqualTo(statCnt);
    }
}
