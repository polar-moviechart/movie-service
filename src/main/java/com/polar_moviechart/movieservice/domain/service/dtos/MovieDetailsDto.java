package com.polar_moviechart.movieservice.domain.service.dtos;

import com.polar_moviechart.movieservice.domain.entity.Movie;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MovieDetailsDto {
    private int code;
    private String title;
    private List<String> poster;
    private String details;
    private LocalDate releaseDate;
    private Integer productionYear;
    private String synopsys;

    private List<MovieDirectorDto> movieDirectorDtos;
    private List<MovieLeadactorDto> movieLeadactorDtos;

    @Builder
    public MovieDetailsDto(int code, String title, String details, LocalDate releaseDate, Integer productionYear, String synopsys, List<MovieDirectorDto> movieDirectorDtos, List<MovieLeadactorDto> movieLeadactorDtos) {
        this.code = code;
        this.title = title;
        this.details = details;
        this.releaseDate = releaseDate;
        this.productionYear = productionYear;
        this.synopsys = synopsys;
        this.movieDirectorDtos = movieDirectorDtos;
        this.movieLeadactorDtos = movieLeadactorDtos;
    }

    public static MovieDetailsDto from(Movie movie) {
        return new MovieDetailsDto(
                movie.getCode(),
                movie.getTitle(),
                movie.getDetails(),
                movie.getReleaseDate(),
                movie.getProductionYear(),
                movie.getSynopsys(),
                MovieDirectorDto.listFrom(movie.getDirectors()),
                MovieLeadactorDto.listFrom(movie.getLeadactors())
        );
    }
}
