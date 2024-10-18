package com.polar_moviechart.movieservice.domain.repository;

import com.polar_moviechart.movieservice.domain.entity.MovieDailyStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieDailyStatsRepository extends JpaRepository<MovieDailyStats, Long> {
    Page<MovieDailyStats> findAllByDate(LocalDate targetDate, Pageable pageable);

    List<MovieDailyStats> findByMovieCodeOrderByDateDesc(int code, Pageable pageable);
}
