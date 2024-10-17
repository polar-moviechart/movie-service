package com.polar_moviechart.movieservice.domain.service;

import com.polar_moviechart.movieservice.domain.entity.MovieDailyStats;
import com.polar_moviechart.movieservice.domain.repository.MovieDailyStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieDailyStatsQueryService {

    private final MovieDailyStatsRepository movieDailyStatsRepository;

    @Transactional(readOnly = true)
    public List<MovieDto> getMovieDailyRankInfo(LocalDate targetDate, Pageable pageable) {
        Page<MovieDailyStats> dailyStats = movieDailyStatsRepository.findAllByDate(targetDate, pageable);
        return dailyStats.getContent().stream()
                .map(stats -> stats.toDto())
                .toList();
    }
}
