package com.polar_moviechart.movieservice.domain.repository;

import com.polar_moviechart.movieservice.domain.entity.MovieDailyStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MovieDailyStatsRepository extends JpaRepository<MovieDailyStats, Long> {
}
