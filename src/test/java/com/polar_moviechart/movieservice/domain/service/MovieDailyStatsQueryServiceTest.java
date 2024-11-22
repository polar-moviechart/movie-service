package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.MovieStatsTestConfig;
import com.polar_moviechart.movieservice.domain.enums.StatType;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDailyStatsResponse;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

class MovieDailyStatsQueryServiceTest extends MovieStatsTestConfig {

    @Autowired
    private MovieDailyStatsQueryService movieDailyStatsQueryService;

    private final static List<Integer> movieCodes = List.of(1);
    private final static LocalDate releaseDate = LocalDate.of(2004, 1, 1);
    private final static int stat = 1;
    private final static int days = 2;
    @BeforeEach
    void setUp() {
        initMovies(movieCodes, releaseDate);
        initStat(stat, days);
    }

    @DisplayName("날짜와 페이징이 적용된 영화 정보를 불러올 수 있다.")
    @Test
    void getMovieDailyRankInfo() {
        // given
        int statCnt = movieCodes.size();
        PageRequest pageable = PageRequest.of(0, statCnt);
        // when
        List<MovieDto> movieDailyRankInfo = movieDailyStatsQueryService.getMovieDailyRankInfo(releaseDate, pageable);
        // then
        Assertions.assertThat(movieDailyRankInfo.size()).isEqualTo(statCnt);
    }

    @DisplayName("영화 코드로 랭킹 정보를 불러올 수 있다.")
    @Test
    void getMovieDailyStats() {
        // given
        int statCnt = movieCodes.size();
        PageRequest pageRequest = PageRequest.of(0, statCnt);
        StatType ranking = StatType.RANKING;
        // when
        MovieDailyStatsResponse movieDailyStats = movieDailyStatsQueryService
                .getMovieDailyStats(movieCodes.get(0), pageRequest, ranking);
        // then
        Assertions.assertThat(movieDailyStats.getCode()).isEqualTo(movieCodes.get(0));
        Assertions.assertThat(movieDailyStats.getStatDtos().size()).isEqualTo(statCnt);
    }
}