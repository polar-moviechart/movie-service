package com.polar_moviechart.movieservice.domain.service.movie;

import com.polar_moviechart.movieservice.domain.entity.MovieDailyStat;
import com.polar_moviechart.movieservice.domain.enums.StatType;
import com.polar_moviechart.movieservice.repository.MovieDailyStatsQueryRepository;
import com.polar_moviechart.movieservice.repository.MovieDailyStatsRepository;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDailyStatsResponse;
import com.polar_moviechart.movieservice.domain.service.dtos.MovieDto;
import com.polar_moviechart.movieservice.domain.service.dtos.StatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieDailyStatsQueryService {

    private final MovieDailyStatsRepository movieDailyStatsRepository;
    private final MovieDailyStatsQueryRepository dailyStatsQueryRepository;

    @Transactional(readOnly = true)
    public List<MovieDto> getMovieDailyRankInfo(LocalDate targetDate, Pageable pageable) {
        Page<MovieDailyStat> dailyStats = movieDailyStatsRepository.findAllByDate(targetDate, pageable);
        return dailyStats.getContent().stream()
                .map(stats -> MovieDto.from(stats.getMovie(), stats.getRanking()))
                .toList();
    }

    public MovieDailyStatsResponse getMovieDailyStats(int code, PageRequest pageable, StatType statType) {
        List<MovieDailyStat> dailyStats = movieDailyStatsRepository
                .findByMovieCodeOrderByDateDesc(code, pageable);

        List<StatDto> statDtos = dailyStats.stream()
                .map(stat -> StatDto.from(stat, statType))
                .toList();
        return new MovieDailyStatsResponse(code, statDtos);
    }

    public LocalDate findLatestDate() {
        return movieDailyStatsRepository
                .findFirstByOrderByDateDesc().getDate();
    }

    public List<LocalDate> getDates() {
        return dailyStatsQueryRepository.getAllDates();
    }
}
