package com.polar_moviechart.movieservice.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "movie_daily_stats")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieDailyStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer ranking;

    @Column(nullable = false)
    private long revenue;

    @Column(nullable = false)
    private Integer audience;

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
    public MovieDailyStat(int ranking, long revenue, LocalDate date, int audience, Movie movie) {
        this.ranking = ranking;
        this.revenue = revenue;
        this.date = date;
        this.audience = audience;
        this.movie = movie;
    }
}
