package com.polar_moviechart.movieservice.domain.repository;

import com.polar_moviechart.movieservice.domain.MovieStatsTestConfig;
import com.polar_moviechart.movieservice.domain.entity.MovieDailyStat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MovieDailyStatsRepositoryTest extends MovieStatsTestConfig {

    @DisplayName("findAllByDate() 쿼리 테스트")
    @Test
    void findAllByDate() {
        // given
        LocalDate targetDate = LocalDate.of(2004, 1, 1);
        PageRequest pageRequest = PageRequest.of(0, 10);
        // when
        Page<MovieDailyStat> movieDailyStatsPage = dailyStatsRepository.findAllByDate(targetDate, pageRequest);
        // then
        assertThat(movieDailyStatsPage.getContent()).isNotEmpty();
    }

    @DisplayName("findByMovieCodeOrderByDateDesc() 쿼리 테스트")
    @Test
    void findByMovieCodeOrderByDateDesc() {
        // given
        int movieCode = 1;
        PageRequest pageRequest = PageRequest.of(0, 10);
        // when
        List<MovieDailyStat> dailyStats = dailyStatsRepository.findByMovieCodeOrderByDateDesc(movieCode, pageRequest);
        // then
        Assertions.assertThat(dailyStats).isNotEmpty();
    }
}
