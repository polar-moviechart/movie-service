package com.polar_moviechart.movieservice.domain.service;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MovieDailyAudience.class, name = "AUDIENCE"),
        @JsonSubTypes.Type(value = MovieDailyRevenue.class, name = "REVENUE"),
        @JsonSubTypes.Type(value = MovieDailyRanking.class, name = "RANKING")
})
public interface MovieDailyStat {
    LocalDate getDate();
}
