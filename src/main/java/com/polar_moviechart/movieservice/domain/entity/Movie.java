package com.polar_moviechart.movieservice.domain.entity;

import com.polar_moviechart.movieservice.domain.service.MovieDirectorDto;
import com.polar_moviechart.movieservice.domain.service.MovieDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "movies")
@RequiredArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private final int code;

    @Column(nullable = false)
    private final String title;

    @Column(nullable = false)
    private final String details;

    @Column
    private final LocalDate releaseDate;

    @Column
    private final Integer productionYear;

    @Column(nullable = false)
    private final String synopsys;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private final List<MovieDailyStats> stats = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "movie")
    @Column(nullable = false)
    private final List<MovieDirector> directors = new ArrayList<>();

    // 기본 생성자 추가
    public Movie() {
        this.code = 0;
        this.title = "";
        this.details = "";
        this.releaseDate = LocalDate.now();
        this.productionYear = 0;
        this.synopsys = "";
    }

    public MovieDto toDto() {
        List<MovieDirectorDto> directors = this.directors.stream()
                .map(director -> director.getDirector().toDto())
                .toList();
        return new MovieDto(
                code,
                title,
                details,
                releaseDate,
                productionYear,
                synopsys,
                directors
        );
    }
}
