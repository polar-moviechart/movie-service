package com.polar_moviechart.movieservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "movies")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private int code;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String details;

    @Column
    private LocalDate releaseDate;

    @Column
    private Integer productionYear;

    @Column(nullable = false)
    private String synopsys;

    @Column
    private Integer likeCnt;

    @Column
    private Double rating;

    @Column(name = "rating_cnt")
    private Integer ratingCnt;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<MovieDailyStat> stats = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "movie")
    @Column(nullable = false)
    private List<MovieDirector> directors = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    @Column(nullable = false)
    private List<MovieLeadactor> leadactors = new ArrayList<>();

    @Builder
    public Movie(int code, String title, String details, LocalDate releaseDate, Integer productionYear, String synopsys, Double rating, Integer ratingCnt, Integer likeCnt) {
        this.code = code;
        this.title = title;
        this.details = details;
        this.releaseDate = releaseDate;
        this.productionYear = productionYear;
        this.synopsys = synopsys;
        this.rating = rating;
        this.ratingCnt = ratingCnt;
        this.likeCnt = likeCnt;
    }

    public void addLikeCount(Integer value) {
        this.likeCnt += value;
    }

    public void addRatingCnt() {
        this.ratingCnt++;
    }

    public void addRating(Double value) {
        this.rating = (this.rating * this.ratingCnt + value) / (this.ratingCnt + 1);
        addRatingCnt();
    }

    public void updateRating(Double newValue, Double oldValue) {
        double newRating = (this.rating * this.ratingCnt - oldValue + newValue) / this.ratingCnt;
        this.rating = newRating;
    }
}
