package com.polar_moviechart.movieservice.domain.entity;

import com.polar_moviechart.movieservice.domain.enums.StatField;
import com.polar_moviechart.movieservice.domain.service.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movie_daily_stats")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieDailyStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private int ranking;

    @Column(nullable = false)
    private int revenue;

    @Column(nullable = false)
    private int audience;

    @Column(nullable = false)
    private LocalDate date;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne 관계 설정
    @JoinColumn(name = "movie_code", referencedColumnName = "code",nullable = false) // 외래 키 설정
    private Movie movie;

    @Builder
    public MovieDailyStats(int ranking, int revenue, LocalDate date, int audience) {
        this.ranking = ranking;
        this.revenue = revenue;
        this.date = date;
        this.audience = audience;
    }

    public MovieDto toMovieDto() {
        return movie.toDto(ranking);
    }

    public MovieDailyStat toDto(StatField statField) {
        if (statField.equals(StatField.RANKING)) {
            return new MovieDailyRanking(date, ranking);
        }
        if (statField.equals(StatField.AUDIENCE)) {
            return new MovieDailyAudience(date, audience);
        } else {
            return new MovieDailyRevenue(date, revenue);
        }
    }
}
