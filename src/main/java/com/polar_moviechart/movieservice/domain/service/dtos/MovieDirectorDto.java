package com.polar_moviechart.movieservice.domain.service.dtos;

import com.polar_moviechart.movieservice.domain.entity.MovieDirector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MovieDirectorDto {
    private final int code;
    private final String name;

    public static MovieDirectorDto from(MovieDirector movieDirector) {
        return new MovieDirectorDto(
                movieDirector.getDirector().getCode(),
                movieDirector.getDirector().getName()
        );
    }

    public static List<MovieDirectorDto> listFrom(List<MovieDirector> directors) {
        return directors.stream()
                .map(MovieDirectorDto::from)
                .toList();
    }
}
