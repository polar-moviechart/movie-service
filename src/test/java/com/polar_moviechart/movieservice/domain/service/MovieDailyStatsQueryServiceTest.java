package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.MovieStatsTestConfig;
import com.polar_moviechart.movieservice.domain.MovieTestConfig;
import com.polar_moviechart.movieservice.domain.enums.StatType;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDailyStatsResponse;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

class MovieDailyStatsQueryServiceTest extends MovieStatsTestConfig {

    @Autowired
    private MovieDailyStatsQueryService movieDailyStatsQueryService;

    @DisplayName("날짜와 페이징이 적용된 영화 정보를 불러올 수 있다.")
    @Test
    void getMovieDailyRankInfo() {
        // given
        PageRequest pageable = PageRequest.of(0,  10);
        LocalDate targetDate = LocalDate.of(2004, 1, 1);
        // when
        List<MovieDto> movieDailyRankInfo = movieDailyStatsQueryService.getMovieDailyRankInfo(targetDate, pageable);
        // then
        Assertions.assertThat(movieDailyRankInfo).isNotEmpty();
    }

    @DisplayName("영화 코드로 랭킹 정보를 불러올 수 있다.")
    @Test
    void getMovieDailyStats() {
        // given
        int movieCode = 1;
        PageRequest pageRequest = PageRequest.of(0, 10);
        StatType ranking = StatType.RANKING;
        // when
        MovieDailyStatsResponse movieDailyStats = movieDailyStatsQueryService.getMovieDailyStats(movieCode, pageRequest, ranking);
        // then
        Assertions.assertThat(movieDailyStats.getCode()).isEqualTo(movieCode);
        Assertions.assertThat(movieDailyStats.getStatDtos()).isNotEmpty();
    }
}