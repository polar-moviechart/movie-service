package com.polar_moviechart.movieservice.domain.service.dtos;

import com.polar_moviechart.movieservice.domain.entity.MovieDailyStat;
import com.polar_moviechart.movieservice.domain.enums.StatType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class StatDto {
    private LocalDate date;
    private Integer value;

    public StatDto(LocalDate date, Integer value) {
        this.date = date;
        this.value = value;
    }

    public static StatDto from(MovieDailyStat stat, StatType statType) {
        if (statType == StatType.AUDIENCE) {
            return new StatDto(stat.getDate(), stat.getAudience());
        } else if (statType == StatType.RANKING) {
            return new StatDto(stat.getDate(), stat.getRanking());
        } else {
            return new StatDto(stat.getDate(), stat.getRevenue());
        }
    }
}
