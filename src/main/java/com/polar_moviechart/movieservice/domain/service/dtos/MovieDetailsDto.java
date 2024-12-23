package com.polar_moviechart.movieservice.domain.service.dtos;

import com.polar_moviechart.movieservice.domain.dto.UserActivityInfo;
import com.polar_moviechart.movieservice.domain.entity.Movie;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MovieDetailsDto {
    private int code;
    private String title;
    private Integer likeCnt;
    private Double rating;
    private Double userMovieRating;
    private List<String> poster;
    private String details;
    private LocalDate releaseDate;
    private Integer productionYear;
    private String synopsys;
    private Boolean isLike;

    private List<MovieDirectorDto> movieDirectorDtos;
    private List<MovieLeadactorDto> movieLeadactorDtos;

    @Builder
    public MovieDetailsDto(int code, String title, Integer likeCnt, Double rating, String details, LocalDate releaseDate, Integer productionYear, String synopsys, List<MovieDirectorDto> movieDirectorDtos, List<MovieLeadactorDto> movieLeadactorDtos) {
        this.code = code;
        this.title = title;
        this.likeCnt = likeCnt;
        this.rating = rating;
        this.details = details;
        this.releaseDate = releaseDate;
        this.productionYear = productionYear;
        this.synopsys = synopsys;
        this.movieDirectorDtos = movieDirectorDtos;
        this.movieLeadactorDtos = movieLeadactorDtos;
    }

    public static MovieDetailsDto from(Movie movie) {
        return MovieDetailsDto.builder()
                .code(movie.getCode())
                .title(movie.getTitle())
                .likeCnt(movie.getLikeCnt())
                .rating(movie.getRating())
                .details(movie.getDetails())
                .releaseDate(movie.getReleaseDate())
                .productionYear(movie.getProductionYear())
                .synopsys(movie.getSynopsys())
                .movieDirectorDtos(MovieDirectorDto.listFrom(movie.getDirectors()))
                .movieLeadactorDtos(MovieLeadactorDto.listFrom(movie.getLeadactors()))
                .build();
    }

    public void setUserMovieRating(Double userMovieRating) {
        if (userMovieRating == null) {
            userMovieRating = 0.0;
        } else {
            this.userMovieRating = userMovieRating;
        }
    }

    public void setUserActivityInfo(UserActivityInfo activityInfo) {
        Boolean likeStatus = activityInfo.getLikeStatus();
        if (likeStatus == true) {
            this.isLike = true;
        } else {
            this.isLike = false;
        }

        if (userMovieRating == null) {
            userMovieRating = 0.0;
        } else {
            this.userMovieRating = activityInfo.getRating();
        }
    }
}
