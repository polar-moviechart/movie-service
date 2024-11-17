package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.BaseTestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class MovieDailyStatsQueryServiceTest extends BaseTestConfig {

    @Autowired
    private MovieDailyStatsQueryService movieDailyStatsQueryService;

    @DisplayName("날짜와 페이징이 적용된 영화 정보를 불러올 수 있다.")
    @Test
    void test() {
        // given
        PageRequest pageable = PageRequest.of(0,  10);
        LocalDate targetDate = LocalDate.of(2004, 1, 2);
        // when
        List<MovieDto> movieDailyRankInfo = movieDailyStatsQueryService.getMovieDailyRankInfo(targetDate, pageable);
        // then
        Assertions.assertThat(movieDailyRankInfo).isNotEmpty();
    }
}