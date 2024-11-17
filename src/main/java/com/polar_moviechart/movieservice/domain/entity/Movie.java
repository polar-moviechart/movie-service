package com.polar_moviechart.movieservice.domain.entity;

import com.polar_moviechart.movieservice.domain.service.MovieDetailsDto;
import com.polar_moviechart.movieservice.domain.service.MovieDirectorDto;
import com.polar_moviechart.movieservice.domain.service.MovieDto;
import com.polar_moviechart.movieservice.domain.service.MovieLeadactorDto;
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
    public Movie(int code, String title, String details, LocalDate releaseDate, Integer productionYear, String synopsys) {
        this.code = code;
        this.title = title;
        this.details = details;
        this.releaseDate = releaseDate;
        this.productionYear = productionYear;
        this.synopsys = synopsys;
    }

    public MovieDto toDto(int ranking) {
        List<MovieDirectorDto> directors = getDirectorDtos();
        List<MovieLeadactorDto> leadactors = getLeadactorDtos();

        return new MovieDto(
                code,
                ranking,
                title,
                List.of(),
                details,
                releaseDate,
                productionYear,
                directors,
                leadactors
        );
    }

    public MovieDetailsDto toDto() {
        List<MovieDirectorDto> directors = getDirectorDtos();
        List<MovieLeadactorDto> leadactors = getLeadactorDtos();

        return new MovieDetailsDto(
                code,
                title,
                List.of(),
                details,
                releaseDate,
                productionYear,
                synopsys,
                directors,
                leadactors
        );
    }

    private List<MovieLeadactorDto> getLeadactorDtos() {
        List<MovieLeadactorDto> leadactors = this.leadactors.stream()
                .map(leadactor -> leadactor.getLeadactor().toDto())
                .toList();
        return leadactors;
    }

    private List<MovieDirectorDto> getDirectorDtos() {
        List<MovieDirectorDto> directors = this.directors.stream()
                .map(director -> director.getDirector().toDto())
                .toList();
        return directors;
    }
}
