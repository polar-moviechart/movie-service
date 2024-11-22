package com.polar_moviechart.movieservice.domain.repository;

import com.polar_moviechart.movieservice.domain.MovieStatsTestConfig;
import com.polar_moviechart.movieservice.domain.entity.MovieDailyStat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    private final static int stat = 1;
    private final static int days = 2;

    @BeforeEach
    void setUp() {
        initMovies(movieCodes, releaseDate);
        initStat(stat, days);
    }

    @DisplayName("findAllByDate() 쿼리 테스트")
    @Test
    void findAllByDate() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 10);
        // when
        Page<MovieDailyStat> movieDailyStatsPage = dailyStatsRepository.findAllByDate(releaseDate, pageRequest);
        // then
        assertThat(movieDailyStatsPage.getContent().size()).isEqualTo(movieCodes.size());
    }

    @DisplayName("findByMovieCodeOrderByDateDesc() 쿼리 테스트")
    @Test
    void findByMovieCodeOrderByDateDesc() {
        // given
        int movieCnt = movieCodes.size();
        PageRequest pageRequest = PageRequest.of(0, movieCnt);
        // when
        List<MovieDailyStat> dailyStats = dailyStatsRepository
                .findByMovieCodeOrderByDateDesc(movieCodes.get(0), pageRequest);
        // then
        assertThat(dailyStats.size()).isEqualTo(movieCnt);
    }

    @DisplayName("DB에 저장된 최신 영화 정보를 가져올 수 있다.")
    @Test
    void findFirstByOrderByDateDesc() {
        // given // when
        MovieDailyStat movieDailyStat = dailyStatsRepository.findFirstByOrderByDateDesc();
        // then
        Assertions.assertThat(movieDailyStat.getDate()).isEqualTo(releaseDate.plusDays(days));
    }
}
