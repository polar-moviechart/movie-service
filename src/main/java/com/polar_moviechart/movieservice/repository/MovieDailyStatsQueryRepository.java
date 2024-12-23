package com.polar_moviechart.movieservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.polar_moviechart.movieservice.domain.entity.QMovieDailyStat.*;

@Repository
@RequiredArgsConstructor
public class MovieDailyStatsQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<LocalDate> getAllDates() {
        return queryFactory
                .select(movieDailyStat.date)
                .from(movieDailyStat)
                .distinct()
                .fetch();
    }

    public LocalDate getStartDate() {
        return queryFactory
                .select(movieDailyStat.date.min())
                .from(movieDailyStat)
                .fetchOne();
    }

    public LocalDate getEndDate() {
        return queryFactory
                .select(movieDailyStat.date.max())
                .from(movieDailyStat)
                .fetchOne();
    }
}
