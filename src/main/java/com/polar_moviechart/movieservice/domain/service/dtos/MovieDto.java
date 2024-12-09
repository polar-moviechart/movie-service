package com.polar_moviechart.movieservice.domain.service.dtos;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MovieDto {
    private int code;
    private int ranking;
    private double rating;
    private String title;
    private String synopsis;
    private List<String> poster;
    private String details;
    private LocalDate releaseDate;
    private Integer productionYear;

    private List<MovieDirectorDto> movieDirectorDtos;
    private List<MovieLeadactorDto> movieLeadactorDtos;

    @Builder
    public MovieDto(int code, int ranking, Double rating, String title, String synopsis, List<String> poster, String details, LocalDate releaseDate, Integer productionYear, List<MovieDirectorDto> movieDirectorDtos, List<MovieLeadactorDto> movieLeadactorDtos) {
        this.code = code;
        this.ranking = ranking;
        this.rating = rating;
        this.title = title;
        this.synopsis = synopsis;
        this.poster = poster;
        this.details = details;
        this.releaseDate = releaseDate;
        this.productionYear = productionYear;
        this.movieDirectorDtos = movieDirectorDtos;
        this.movieLeadactorDtos = movieLeadactorDtos;
    }

    public static MovieDto from(Movie movie, int ranking) {
        return MovieDto.builder()
                .code(movie.getCode())
                .ranking(ranking)
                .rating(movie.getRating())
                .title(movie.getTitle())
                .synopsis(movie.getSynopsys())
                .details(movie.getDetails())
                .releaseDate(movie.getReleaseDate())
                .productionYear(movie.getProductionYear())
                .movieDirectorDtos(MovieDirectorDto.listFrom(movie.getDirectors()))
                .movieLeadactorDtos(MovieLeadactorDto.listFrom(movie.getLeadactors()))
                .build();
    }
}
